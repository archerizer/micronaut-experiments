package com.archerizer.experiments.micronaut.issue.host;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Produces;

import java.util.Optional;

@Controller("/headers")
public class HeaderApi {

    @Get("/host")
    @Produces(MediaType.TEXT_PLAIN)
    String hostResponse(@Header("Host") String hostHeader) {
        return Optional.ofNullable(hostHeader).orElse("No host header!");
    }

    @Get("/notHost")
    @Produces(MediaType.TEXT_PLAIN)
    String noHostResponse(@Header("header") String header) {
        return Optional.ofNullable(header).orElse("No header!");
    }
}
