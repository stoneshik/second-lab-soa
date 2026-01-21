package lab.soa.infrastructure.config;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import lab.soa.presentation.resources.FlatResource;

@ApplicationPath("/api/v1")
public class JaxRsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(FlatResource.class);
        return classes;
    }
}
