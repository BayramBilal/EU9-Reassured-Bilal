package com.cydeo.Practice;

import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanPOSTRequests {

    @BeforeAll
    public static void setUpClass(){


        baseURI = "http://54.88.101.116:8000";
    }
    @Test
    public void PostWithString(){

        // sending as a string
        Response response =  given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Melih\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 2348695106\n" +
                        "}\n")
                .when().post("/api/spartans/");

        // validations
        // verify status code is 201

        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());

        // verify success method
        assertEquals("A Spartan is Born!", response.path("success"));

        // verify request body
        JsonPath jsonPath = response.jsonPath();

        assertEquals(jsonPath.getString("data.name"), "Melih");
        assertEquals(jsonPath.getString("data.gender"), "Male");
        assertEquals(jsonPath.getLong("data.phone"), 2348695106l);
}
    @Test
    public void PostWithMap(){

        // sending a map to be used as a body for post request

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Tareq");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 56565757567l);


       Response response =  given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans/");

       assertEquals(201, response.statusCode());
       response.prettyPrint();
    }
    @Test
    public void PostWithPOJO(){

        // create spartan object and used as a body for post request

        Spartan spartanPojo = new Spartan();
        spartanPojo.setName("Ahmet");
        spartanPojo.setGender("Male");
        spartanPojo.setPhone(5454545265l);

        Response response =  given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartanPojo)
                .when().post("/api/spartans/");

        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
    }
       @Test
        public void GetREquestWithPOJO(){

           Response response1 = given().accept(ContentType.JSON)
                .and().pathParam("id", 138)
                .when().get("/api/spartans/{id}");

        Spartan spartanResponse = response1.as(Spartan.class);
        System.out.println("spartanResponse = " + spartanResponse.toString());

    }
}
