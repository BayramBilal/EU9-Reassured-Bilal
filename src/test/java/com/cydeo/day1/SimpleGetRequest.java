package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

public class SimpleGetRequest {


    String url = "http://54.88.101.116:8000/api/spartans";

    @Test
    public void test1(){
        Response response = RestAssured.get(url);

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();


    }








}
