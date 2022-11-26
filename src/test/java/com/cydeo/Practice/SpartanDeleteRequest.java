package com.cydeo.Practice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanDeleteRequest {

    @BeforeAll
    public static void setUpClass(){


        baseURI = "http://54.88.101.116:8000";
    }
    @Test
    public void DeleteRequest(){

                   // we gonna send request body with updated value, and content type header
           given().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                    .and().pathParam("id", 126)
                  .when().delete("/api/spartans/{id}")
                    .then().assertThat().statusCode(204);



            given().accept(ContentType.JSON)
                    .and().pathParam("id", 126)
                    .when().get("/api/spartans/{id}")
                    .then().assertThat().statusCode(404);


    }


    }
