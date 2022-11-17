package com.cydeo.Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestsWithQueryParameters {

    @BeforeAll
    public static void setUpClass(){

       RestAssured.baseURI = "http://54.88.101.116:8000";

}

    @Test
    public void QueryParam1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get(baseURI + "/api/spartans/search");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Female"));

        // male not exists

        Assertions.assertFalse(response.body().asString().contains("Male"));


        Assertions.assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();
}
    @Test
    public void queryParams2(){

        //creating Map for query params

        Map<String, Object> paramsMap = new HashMap<>();
       paramsMap.put("gender", "Female");
       paramsMap.put("nameContains", "Janette");

       // send request

        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get(baseURI + "/api/spartans/search");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Female"));

        // male not exists

        Assertions.assertFalse(response.body().asString().contains("Male"));


        Assertions.assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();
    }
}