package com.archerizer.experiments.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/greeting")
public class GreetingApi {
    @Get("/{message}")
    @Produces(MediaType.APPLICATION_JSON)
    Greeting getGreeting(String message) {
        return new Greeting(message);
    }
}
