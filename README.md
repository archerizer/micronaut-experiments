### Description

`HttpClient.exchange()`, at least when used in a test, overrides the value of the "Host" header before making the actual
HTTP request even if it is explicitly set for its `HttpRequest`.

To demonstrate this, a simple example is created with a controller that takes a header name as a path variable and
exposes its value in the response body:

```java
@Controller("/headers")
public class HeadersApi {

    @Get("/{headerName}")
    @Produces(MediaType.TEXT_PLAIN)
    String hostResponse(@PathVariable("headerName") String headerName, HttpHeaders headers) {
        return headers.get(headerName);
    }
}
```

### Steps to Reproduce

Run the parameterized test in the sample application. The test effectively performs the following:

```java
var name = "host";
var server = ApplicationContext.run(EmbeddedServer.class);
var client = HttpClient.create(server.getURL());
var request = HttpRequest.GET("/headers/" + name).header(name, "VALUE");
var responseBody = client.toBlocking().exchange(request, String.class).body();
```

If `name` is set to `Host` or `host`, the host header is overridden by the `HttpClient` instance before making the
actual request. 

### Expected Behaviour

The actual HTTP request sent by `HttpClient` should reflect all the headers that are specified when building the
`HttpRequest` it is instructed to send.

### Actual Behaviour

`HttpClient` overrides the value of the `host` header, but not custom headers:

```text
Expecting:
 <"localhost:54467">
to be equal to:
 <"HeaderValue">
but was not.
Expected :HeaderValue
Actual   :localhost:54467
```

### Environment Information

- **Operating System**: Any
- **Micronaut Version:** 1.0.3
- **JDK Version:** Any OpenJDK 11 build

### Example Application

- https://github.com/archerizer/micronaut-experiments/tree/issue/http-client-host-header
