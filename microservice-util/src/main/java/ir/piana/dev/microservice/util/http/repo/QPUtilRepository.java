package ir.piana.dev.microservice.util.http.repo;

import ir.piana.dev.microservice.context.http.QPHttpRepository;
import ir.piana.dev.microservice.context.module.QPStaticResourceResolverModule;
import ir.piana.dev.microservice.context.staticresource.QPStaticResource;
import ir.piana.dev.microservice.core.http.QPHttpException;
import ir.piana.dev.microservice.core.http.QPHttpHandler;
import ir.piana.dev.microservice.core.http.QPHttpStatus;
import ir.piana.dev.microservice.core.module.QPBaseModule;

/**
 * @author Mohammad Rahmati, 2/17/2019
 */
public class QPUtilRepository extends QPHttpRepository {
    public QPHttpHandler staticResourceHandler = (config, request, response) -> {
        String moduleName = config.getValue("static-resolver-module-name");
        QPStaticResource staticResource = null;
        try {
            staticResource = QPBaseModule.getModule(
                    moduleName, QPStaticResourceResolverModule.class)
                    .resolve(request.getAsteriskPath());
        } catch (Exception e) {
            throw new QPHttpException(QPHttpStatus.NOT_FOUND_404);
        }
        response.setEntity(staticResource);
    };
}
