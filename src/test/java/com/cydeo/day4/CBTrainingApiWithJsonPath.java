package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFormat;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://api.training.cydeo.com";

    }

    @DisplayName ("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */

             Response response = given()
                    .accept(ContentType.JSON)
                    .and().pathParam("id", 23401 )
                    .log().all()
                    .when().get(baseURI + "/students/{id}");

            assertEquals(200, response.statusCode());
        //    assertEquals("application/json;charset=UTF-8", response.contentType());


        assertEquals("gzip", response.header("Content-Encoding"));
        assertEquals("Date", response.header("Date"));


        int batch =  response.path("batch");
        int section =  response.path("section");
        String firstName = response.path("name");
        String emailAddress = response.path("email");
        String companyName = response.path("company");
        String state = response.path("state");
       int zipcode = response.path("zipcode");


        // assert values
        assertEquals("Vera", firstName);
        assertEquals(14, batch);
        assertEquals(12, section);
        assertEquals("aaa@gmail.com", emailAddress);
        assertEquals("Cydeo", companyName);
        assertEquals("IL",state);
        assertEquals(60606, zipcode);
    }
}
