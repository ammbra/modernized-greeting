package com.redhat.developers;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testCreate() {
        Message message = new Message();
        given().contentType(ContentType.JSON).body(message)
          .when().post("/messages")
          .then()
             .statusCode(200)
             .body(notNullValue());
    }

}