package com.devscompass.quarkustemplate.api.rest.ping;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PingResourceTest {

  @Test
  void testPingEndpoint() {
    given()
        .when()
        .get("/ping")
        .then()
        .statusCode(200)
        .contentType(MediaType.TEXT_PLAIN)
        .body(is("pong\n"));
  }
}
