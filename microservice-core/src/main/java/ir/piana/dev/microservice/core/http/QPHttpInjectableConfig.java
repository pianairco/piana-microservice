package ir.piana.dev.microservice.core.http;

import org.jdom2.Element;

import java.util.Map;
import java.util.Set;

/**
 * @author Mohammad Rahmati, 2/16/2019
 */
public interface QPHttpInjectableConfig {
    Set<String> getKeys();
    String getValue(String key);
}
