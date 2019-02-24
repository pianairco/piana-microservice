package ir.piana.dev.microservice.sample;

import ir.piana.dev.microservice.context.http.QPHttpRepository;
import ir.piana.dev.microservice.core.http.QPHttpHandler;
import ir.piana.dev.microservice.sample.useraddress.entity.UserAddressEntity;
import ir.piana.dev.microservice.sample.useraddress.repo.UserAddressRepository;

/**
 * @author Mohammad Rahmati, 2/16/2019
 */
public class HelloWorld extends QPHttpRepository {
    public QPHttpHandler handler1 = (principal, config, request, response) -> {
        response.setEntity("Hello World!");
        UserAddressRepository userRepository = springBeanProvider
                .getBean(UserAddressRepository.class);
        UserAddressEntity userEntity = userRepository.findByPrincipalEntity(principal);
        System.out.println(userEntity.getAddress());
    };

    public QPHttpHandler handler2 = (principal, config, request, response) -> {
        response.setEntity("Hello World!");
//        UserRepository userRepository = springBeanProvider
//                .getBean(UserRepository.class);
//        UserEntity userEntity = userRepository.findById(1);
//        System.out.println(userEntity.getUsername());
    };
}
