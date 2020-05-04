package com.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserInfoResourceTest {

  String payload = "{\"firstName\":\"Gildong\",\"lastName\":\"Hong\",\"inputDate\":\"Sat May 02 14:13:43 KST 2020\",\"readCount\":149}";

  @Test
  public void testHelloEndpoint() {
    given()
        .contentType(ContentType.JSON)
        .body(payload)
        .when()
        .post("/userinfo")
        .then()
        .statusCode(200);
  }

}