

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ApiTest {

    private final String BASE_URL = "https://postman-echo.com";


    @Test
    @DisplayName("GET test")
    public void test1() {

        RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .get("get")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("url", startsWith("https://postman-echo.com/get"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo("*/*"))
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }


    @Test
    @DisplayName("POST raw test")
    public void test2() {

        Map<String, String> params = new HashMap<>();
        params.put("test", "value");

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .params(params)
                .post("post")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("data", equalTo("test=value"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.JSON.getAcceptHeader()));
    }


    @Test
    @DisplayName("POST form test")
    public void test3() {

        String json = "{\n" +
                "        \"foo1\": \"bar1\",\n" +
                "        \"foo2\": \"bar2\"\n" +
                "    }";

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(json)
                .post("post")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("json", equalTo(json))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.JSON.getAcceptHeader()));
    }



}
