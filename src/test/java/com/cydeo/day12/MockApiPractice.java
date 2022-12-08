package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class MockApiPractice {

    // https://d2ce2300-b2bb-4aae-9e5f-268a23e8ded0.mock.pstmn.io


    @Test
    public void test1(){
        given().baseUri("https://d2ce2300-b2bb-4aae-9e5f-268a23e8ded0.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", is("John"));

    }
    @Test
    public void test2(){
        given().baseUri("https://d2ce2300-b2bb-4aae-9e5f-268a23e8ded0.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();


    }
}
