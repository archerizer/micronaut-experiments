### Description

`HttpClient.exchange()`, at least when used in a test, overrides the value of the "Host" header before making the actual HTTP request even if it is explicitly set for its `HttpRequest`.

### Steps to Reproduce

In a test, do:

var server = ApplicationContext.run(EmbeddedServer.class);
var client = HttpClient.create(server.getURL());
var request = HttpRequest.GET("/api").header("Host", "overridden");
var response = client.toBlocking().exchange(request)

### Expected Behaviour

The HTTP request should have its "Host" header set to "overridden".

### Actual Behaviour

The HTTP request has a "Host" header set to "localhost

### Environment Information

- **Operating System**: Ubuntu
- **Micronaut Version:** 1.0.3
- **JDK Version:** 11

### Example Application

- https://github.com/archerizer/micronaut-experiments/tree/issue/http-client-host-header
