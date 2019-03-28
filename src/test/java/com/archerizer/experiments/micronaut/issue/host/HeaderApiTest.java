package com.archerizer.experiments.micronaut.issue.host;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("host header Api tests")
class HeaderApiTest {

    protected EmbeddedServer server;

    protected HttpClient client;

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

    @Test
    @DisplayName("Returns header value as robyn")
    void returnsRequestedHeader() throws IOException {

        var request = HttpRequest.GET("/headers/notHost").header("header", "robyn");

        var response = client.toBlocking().exchange(request, String.class).body();

        assertThat(response).isEqualTo("robyn");
    }

    @Test
    @DisplayName("Returns Host header value as robyn")
    void returnsRequestedHostHeader() throws IOException {

        var request = HttpRequest.GET("/headers/host").header("Host", "robyn");

        var response = client.toBlocking().exchange(request, String.class).body();

        assertThat(response).isEqualTo("robyn");
    }
}
