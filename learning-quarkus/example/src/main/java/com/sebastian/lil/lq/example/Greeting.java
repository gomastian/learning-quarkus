package com.sebastian.lil.lq.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class Greeting {
    GreetingConfig config;

    @Inject
    public Greeting(GreetingConfig config){
        this.config = config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting(){
        return "Hello " + config.getReceipient();
    }
}
