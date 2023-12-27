

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ApiTest {

    private final String BASE_URL = "https://postman-echo.com";

    private final Map<String, String> paramsEmpty = new HashMap<>();


    @Test
    @DisplayName("GET test")
    public void test() {

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .when()
                .get("get")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", equalTo(BASE_URL + "/get"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()))
                .body("args", equalTo(paramsEmpty));
    }

    @Test
    @DisplayName("GET with query test")
    public void test1() {

        Map<String, String> params = new HashMap<>();
        params.put("foo1", "bar1");
        params.put("foo2", "bar2");

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .when()
                .queryParams(params)
                .get("get")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", startsWith(BASE_URL + "/get"))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()))
                .body("args", equalTo(params));
    }


    @Test
    @DisplayName("POST raw test")
    public void test2() {

        String json = "{\n" +
                "    \"test\": \"value\"\n" +
                "}";

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .contentType(ContentType.TEXT)
                .body(json)
                .post("post")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", equalTo(BASE_URL + "/post"))
                .body("data", equalTo(json))
                .body("files", equalTo(paramsEmpty))
                .body("form", equalTo(paramsEmpty))
                .body("args", equalTo(paramsEmpty))
                .body("json", IsNull.nullValue())
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()));
    }


    @Test
    @DisplayName("POST form test")
    public void test3() {

        Map<String, String> params = new HashMap<>();
        params.put("foo1", "bar1");
        params.put("foo2", "bar2");

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .params(params)
                .request()
                .post("post")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", equalTo(BASE_URL + "/post"))
                .body("form", equalTo(params))
                .body("json", equalTo(params))
                .body("files", equalTo(paramsEmpty))
                .body("args", equalTo(paramsEmpty))
                .body("data", new IsSame<String>(""))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()));
    }

    @Test
    @DisplayName("PUT test")
    public void test4() {

        String data = "This is expected to be sent back as part of response body.";

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .contentType(ContentType.TEXT)
                .body(data)
                .put("put")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", equalTo(BASE_URL + "/put"))
                .body("data", equalTo(data))
                .body("json", IsNull.nullValue())
                .body("files", equalTo(paramsEmpty))
                .body("form", equalTo(paramsEmpty))
                .body("args", equalTo(paramsEmpty))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()));
    }


    @Test
    @DisplayName("PATCH test")
    public void test5() {

        String data = "This is expected to be sent back as part of response body.";

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .contentType(ContentType.TEXT)
                .body(data)
                .patch("patch")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", equalTo(BASE_URL + "/patch"))
                .body("data", equalTo(data))
                .body("json", IsNull.nullValue())
                .body("files", equalTo(paramsEmpty))
                .body("form", equalTo(paramsEmpty))
                .body("args", equalTo(paramsEmpty))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()));
    }

    @Test
    @DisplayName("DELETE test")
    public void test6() {

        String data = "This is expected to be sent back as part of response body.";

        RestAssured.given()
                .baseUri(BASE_URL)
                .accept(ContentType.ANY)
                .contentType(ContentType.TEXT)
                .body(data)
                .delete("delete")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("url", equalTo(BASE_URL + "/delete"))
                .body("data", equalTo(data))
                .body("json", IsNull.nullValue())
                .body("files", equalTo(paramsEmpty))
                .body("form", equalTo(paramsEmpty))
                .body("args", equalTo(paramsEmpty))
                .body("headers.x-amzn-trace-id", startsWith("Root="))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/17.0.9)"))
                .body("headers.accept", equalTo(ContentType.ANY.getAcceptHeader()));
    }


}
