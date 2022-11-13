package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.baseURI;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init(){

        baseURI = "http://54.88.101.116:1000/ords/hr";

    }
   /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName ("GET request to /countries with query param ")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when().get("/countries");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();
    }

    @DisplayName ("GET request to /employees with query param")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all()
                .when().get("/employees");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();
    }



}
