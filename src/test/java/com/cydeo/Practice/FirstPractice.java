package com.cydeo.Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FirstPractice {

    String spartanBaseUrl = "http://54.88.101.116:8000";
    Response response;

    @Test
    public void test1(){

        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");
        // print status code
        int status = response.statusCode();
        System.out.println("status = " + status);

       // System.out.println("response.body().asString() = " + response.body().asString());
        System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());

    }

    @Test
    public void test2(){

        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");
        // print status code
        int status = response.statusCode();
        System.out.println("status = " + status);

       // body contains Allen

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertTrue(response.body().asString().contains("Allen"));
    }
    @Test
    public void test3(){

        response =  RestAssured.given().accept(ContentType.JSON)
                            .when()
               .get(spartanBaseUrl + "/api/spartans");


        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());
    }
}