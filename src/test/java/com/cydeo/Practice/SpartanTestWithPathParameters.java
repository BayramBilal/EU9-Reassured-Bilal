package com.cydeo.Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestWithPathParameters {

    @BeforeAll
    public static void setUpClass(){

        RestAssured.baseURI = "http://54.88.101.116:8000";

    }

    @Test
    public void PathParam1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get(baseURI + "/api/spartans/{id}");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Allen"));

}
}