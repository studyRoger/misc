package org.home.bookmart;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by roger on 9/13/15.
 */
@ApplicationPath("/v1")
public class BookmartApplication extends ResourceConfig {
    public BookmartApplication() {
        register(JacksonFeature.class);

        packages("org.home.bookmart.resource");
    }

}
