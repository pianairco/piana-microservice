package ir.piana.dev.microservice.core.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Mohammad Rahmati, 2/22/2019
 */
@Configuration
@ImportResource({"file:./applicationContext.xml"})
class QPSpringConfiguration {
}
