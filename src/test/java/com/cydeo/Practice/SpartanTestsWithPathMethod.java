package com.cydeo.Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPathMethod {

    @BeforeAll
    public static void setUpClass(){

        RestAssured.baseURI = "http://54.88.101.116:8000";

    }

    @Test
    public void PathMethod1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get(baseURI + "/api/spartans/{id}");

       assertEquals(200, response.statusCode());

       assertEquals("application/json", response.contentType());

        System.out.println("Id: " + response.body().path("id").toString());
        System.out.println("name: " + response.body().path("name").toString());
        System.out.println("gender: " + response.body().path("gender").toString());
        System.out.println("phone " + response.body().path("phone").toString());

        int id = response.path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
       long phone = response.body().path("phone");

       assertEquals(10, id);
       assertEquals("Lorenza", name);
       assertEquals("Female", gender);
       assertEquals(3312820936l, phone);

    }

    @Test
    public void PathMethod2(){
        Response response= RestAssured.get(baseURI + "/api/spartans");
        //extract id
    int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);
        //extract name

        String firstName= response.path("name[0]");
        System.out.println("firstName = " + firstName);

        //get the last first name
        
        String lastFirstname = response.path("name[-1]");
        System.out.println("lastFirstname = " + lastFirstname);

        //extract all firstnames and print them

        List<String> allFirstNames = response.path("name");

        System.out.println(allFirstNames);

        System.out.println("allFirstNames.size() = " + allFirstNames.size());

        List<Object> phoneNums = response.path("phone");
                System.out.println("phoneNums = " + phoneNums);
            System.out.println("phoneNums size= " + phoneNums.size());
        }


        }




