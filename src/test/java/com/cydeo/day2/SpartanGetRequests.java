package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanGetRequests {



    //    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result
    String baseUrl = "http://54.88.101.116:8000";

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl+ "/api/spartans");


        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //priting response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing then ?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(),"application/json");


    }
 /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */
    @DisplayName("GET one spartan / api/spartans/3 and verify")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl+"/api/spartans/3");


        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.contentType(), "application/json");
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }
     /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("GET request to /api/hello")
    @Test
    public void test3(){
        Response response = RestAssured.when().get(baseUrl+"/api/hello");


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        Assertions.assertEquals("17", response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta", response.body().asString());


    }
    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test4(){
        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get(baseUrl+"/api/spartans/10");

        //verify status code is 406
        Assertions.assertEquals(406,response.statusCode());

        //verify content type
        assertEquals("application/xml;charset=UTF-8",response.contentType());
    }
}