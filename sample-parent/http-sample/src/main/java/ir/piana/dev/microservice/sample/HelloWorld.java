package ir.piana.dev.microservice.sample;

import ir.piana.dev.microservice.context.http.QPHttpRepository;
import ir.piana.dev.microservice.core.http.QPHttpHandler;
import ir.piana.dev.microservice.sample.usermodule.entity.UserEntity;
import ir.piana.dev.microservice.sample.usermodule.repo.UserRepository;

/**
 * @author Mohammad Rahmati, 2/16/2019
 */
public class HelloWorld extends QPHttpRepository {
    public QPHttpHandler handler1 = (config, request, response) -> {
        response.setEntity("Hello World!");
        UserRepository userRepository = springBeanProvider
                .getBean(UserRepository.class);
        UserEntity userEntity = userRepository.findById(1);
        System.out.println(userEntity.getUsername());
    };

    public QPHttpHandler handler2 = (config, request, response) -> {
        response.setEntity("Hello World!");
        UserRepository userRepository = springBeanProvider
                .getBean(UserRepository.class);
        UserEntity userEntity = userRepository.findById(1);
        System.out.println(userEntity.getUsername());
    };
}
