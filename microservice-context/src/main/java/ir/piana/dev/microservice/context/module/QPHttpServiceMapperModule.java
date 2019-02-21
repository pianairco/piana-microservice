package ir.piana.dev.microservice.context.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.piana.dev.microservice.core.QPException;
import ir.piana.dev.microservice.core.http.*;
import ir.piana.dev.microservice.core.module.QPBaseModule;
import ir.piana.dev.microservice.context.http.QPHttpRepositoryManagerBuilder;
import ir.piana.dev.microservice.context.http.construct.QPHandlerConstruct;
import ir.piana.dev.microservice.context.http.construct.QPRepositoryConstruct;
import ir.piana.dev.microservice.context.http.util.QPHttpInjectableConfigImpl;
import ir.piana.dev.microservice.core.spring.QPSpringContext;
import org.jpos.q2.QBean;
import org.jpos.space.SpaceListener;
import org.jpos.transaction.Context;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QPHttpServiceMapperModule
        extends QPBaseModule
        implements SpaceListener<String, Context> {
    private Map<String, Class> repoMap = new LinkedHashMap<>();
    private Map<String, QPHttpRepositoryManager> repositoryManagerMap
            = new LinkedHashMap<>();
    private Map<String, QPHandlerConstruct> handlerConstructMap =
            new LinkedHashMap<>();
    protected Map<String, QPHandlerConstruct> httpAsteriskHandlerConstructMap =
            new LinkedHashMap<>();

    protected QPHttpAuthenticator authenticator;

    protected ExecutorService listener;
    protected ExecutorService worker;
    protected static Gson gson;

    protected String springContextName;
    protected QPSpringContext springContext;

    static {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.serializeNulls().create();
    }

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        springContextName = getPersist().getChildText("qp-spring-context");
        springContextName = springContextName == null ?
                "default" : springContextName;

        getPersist().getChildren("qp-repository")
                .parallelStream().forEach(repoElement -> {
            try {
                QPRepositoryConstruct repositoryConstruct = new QPRepositoryConstruct();
                repositoryConstruct.setName(repoElement.getAttributeValue("name"));
                repositoryConstruct.setScope(repoElement
                        .getAttributeValue("scope", "singleton"));
                Class c = Class.forName(repoElement.getAttributeValue("class"));
                repositoryConstruct.setaClass(c);
                repositoryConstruct.setSingletonInstance(c.newInstance());
                repositoryManagerMap.put(repositoryConstruct.getName(),
                        QPHttpRepositoryManagerBuilder.build(repositoryConstruct));
//                repoMap.put(repoElement.getAttributeValue("name"),
//                        Class.forName(repoElement.getAttributeValue("class")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (QPException e) {
                e.printStackTrace();
            }
        });

        getPersist().getChildren("qp-handler")
                .parallelStream().forEach(handlerElement -> {
            QPHandlerConstruct handlerConstruct = new QPHandlerConstruct();
            String url = handlerElement.getAttributeValue("url");
            handlerConstruct.setMethod(handlerElement.getAttributeValue("method"));
            handlerConstruct.setRoles(handlerElement.getAttributeValue("roles"));
            String repoName = handlerElement.getAttributeValue("repository");
            handlerConstruct.setRepoManager(repositoryManagerMap.get(repoName));
            String handlerName = handlerElement.getAttributeValue("handler");
            handlerConstruct.setHandlerName(handlerName);
//            try {
//                Method.class.getField(handlerName);
//                Field field = repoMap.get(repoName).getField(handlerName);
//                if (QPHttpHandler.class.isAssignableFrom(field.getType())) {
//                    QPHttpHandler handler = (QPHttpHandler) field.get(null);
//                    handlerConstruct.setHandler(
//                            handler);
//                }
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
            handlerConstruct.setHandlerConfig(QPHttpInjectableConfigImpl
                    .build(handlerElement.getChildren("property")));

            if (url.contains("**")) {
                handlerConstruct.setUrl(url.substring(
                        0, url.indexOf("/**")));
                httpAsteriskHandlerConstructMap.put(
                        handlerConstruct.getMethod()
                                .concat(":")
                                .concat(url.substring(0, url.indexOf("/**"))),
                        handlerConstruct);
            } else {
                handlerConstruct.setUrl(url);
                handlerConstructMap.put(
                        handlerConstruct.getMethod()
                                .concat(":")
                                .concat(handlerConstruct.getUrl()),
                        handlerConstruct);
            }
        });
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
        listener = Executors.newSingleThreadExecutor();
        worker = Executors.newSingleThreadExecutor();
    }

    @Override
    protected void configForSpringContext() throws Exception {

    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {
//        springContext = QPBaseModule.getModule(
//                springContextName,
//                QPSpringContextProviderModule.class);
    }

    @Override
    protected void startQPModule() throws Exception {
        listener.submit(() -> {
            while (getState() == QBean.STARTED) {
                try {
                    Context context = in(Context.class);
                    worker.execute(() -> {
                        processRequest(context);
                    });
                } catch (QPException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void stopQPModule() throws Exception {

    }

    @Override
    protected void destroyQPModule() throws Exception {

    }

    @Override
    public void notify(String key, Context value) {

    }

    protected void processRequest(Context context) {
        QPHttpRequest request = null;
        QPHttpResponse response = null;
        try {
            request = context.get("qp-http-request");
            response = context.get("qp-http-response");
            String requestKey = request.getMethodType().getName()
                    .concat(":")
                    .concat(request.getRequestURI());
            QPHandlerConstruct handlerConstruct = handlerConstructMap
                    .get(requestKey);
            String asteriskPath = "";
            if (handlerConstruct == null) {
                String selectedKey = "";
                for (String key : httpAsteriskHandlerConstructMap.keySet()) {
                    if (requestKey.startsWith(key)) {
                        if (selectedKey.length() < key.length()) {
                            selectedKey = key;
                        }
                    }
                }
                if (!selectedKey.isEmpty()) {
                    handlerConstruct = httpAsteriskHandlerConstructMap
                            .get(selectedKey);
                    asteriskPath = requestKey.substring(selectedKey.length());
                    request.setAsteriskPath(asteriskPath);
                }
            }
            if(handlerConstruct != null) {
                invokeHttpHandler(
                        handlerConstruct,
                        request, response);
            } else {
                throw new QPHttpException(QPHttpStatus.NOT_FOUND_404);
            }
        } catch (QPHttpException e) {
            e.applyResponse(response);
        } catch (QPException e) {
            new QPHttpException(
                    QPHttpStatus.INTERNAL_SERVER_ERROR_500)
                    .applyResponse(response);
        } catch (Exception e) {
            new QPHttpException(
                    QPHttpStatus.INTERNAL_SERVER_ERROR_500)
                    .applyResponse(response);
        }
    }

//    protected void authorizeHttpHandler(
//            QPHandlerConstruct handlerConstruct,
//            QPHttpRequest request, QPHttpResponse response)
//            throws QPException {
//        handlerConstruct.getRepoManager()
//                .resolve(handlerConstruct.getHandlerName())
//                .handle(handlerConstruct.getHandlerConfig(),
//                        springContext,
//                        request, response);
//        response.apply();
//    }

    protected void invokeHttpHandler(
            QPHandlerConstruct handlerConstruct,
            QPHttpRequest request, QPHttpResponse response)
            throws QPException {
        handlerConstruct.getRepoManager()
                .resolve(handlerConstruct.getHandlerName())
                .handle(handlerConstruct.getHandlerConfig(),
                        pianaSpringContextFactory
                                .getApplicationContext(springContextName),
                        request, response);
        response.apply();
    }

//    protected void invokeHttpOperator(
//            QPServiceConstruct serviceConstruct,
//            QPHttpRequest request, QPHttpResponse response)
//            throws Exception {
//        String[] split = serviceConstruct.getHandler().split(":");
//        String handlerClass = pkgMap.get(
//                serviceConstruct.getPkgName())
//                .concat(".")
//                .concat(split[0]);
//        Class<?> aClass = Class.forName(handlerClass);
//        Method method = aClass.getMethod(
//                split[1], QPHttpRequest.class, QPHttpResponse.class);
//        method.invoke(null, request, response);
//        response.apply();
//    }
}
