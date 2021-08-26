package com.horus.velograph.conf;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("api")
public class JerseyConfig  extends ResourceConfig {

    public JerseyConfig() {

        packages("com.horus.velograph.resource");
        packages("com.horus.velograph.controller");
 //       register(AuthenticationResource.class);
 //       register(AccessDeniedExceptionMapper.class);
 //       register(AuthenticationExceptionMapper.class);
 //       register(DataNotFoundExceptionMapper.class);
    }
}