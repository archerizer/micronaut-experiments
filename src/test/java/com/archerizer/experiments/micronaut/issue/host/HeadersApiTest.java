package com.archerizer.experiments.micronaut.issue.host;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
class HeadersApiTest {

    private EmbeddedServer server;

    private HttpClient client;

    @BeforeAll
    void setupServerAndClient() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = HttpClient.create(server.getURL());
    }

    @AfterAll
    void shutdownServer() {
        server.close();
        client.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"host", "Host", "custom", "Name"})
    void returnsCustomHeaderInResponseBody(String headerName) throws IOException {

        var request = HttpRequest
            .GET("/headers/" + headerName)
            .header(headerName, "HeaderValue");

        var responseBody = client.toBlocking().exchange(request, String.class).body();

        assertThat(responseBody).isEqualTo("HeaderValue");
    }
}
