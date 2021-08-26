package com.horus.velograph.conf;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;


import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

import com.horus.velograph.controller.*;
import com.horus.velograph.resource.*;


@ApplicationPath("/api")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
          Arrays.asList(
            GiftResource.class, 
            CountryController.class)); 
    //        NotFoundExceptionHandler.class, 
    //        AlreadyExistsExceptionHandler.class));
    }
}