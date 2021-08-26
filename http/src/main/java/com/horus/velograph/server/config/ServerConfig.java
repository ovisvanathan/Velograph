package com.horus.velograph.server.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/*")
public class ServerConfig extends ResourceConfig {

    public ServerConfig() {
        packages("com.horus.velograph.resource");
        packages("com.horus.velograph.controller");
    }

}