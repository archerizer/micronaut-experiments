package com.archerizer.experiments.micronaut.issue.host;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;

@Controller("/headers")
public class HeadersApi {

    @Get("/{headerName}")
    @Produces(MediaType.TEXT_PLAIN)
    String hostResponse(@PathVariable("headerName") String headerName, HttpHeaders headers) {
        return headers.get(headerName);
    }
}
