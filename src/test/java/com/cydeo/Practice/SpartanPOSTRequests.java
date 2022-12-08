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


        baseURI = "http://3.91.214.5:7000";
    }
    @Test
    public void PostWithString(){

        // sending as a string
        Response response =  given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Murat\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 2248695106\n" +
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

        assertEquals(jsonPath.getString("data.name"), "Murat");
        assertEquals(jsonPath.getString("data.gender"), "Male");
        assertEquals(jsonPath.getLong("data.phone"), 2248695106l);
}
    @Test
    public void PostWithMap(){

        // sending a map to be used as a body for post request

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Bebeto");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 12365757567l);


       Response response =  given().auth().basic("admin", "admin").accept(ContentType.JSON)
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
        spartanPojo.setName("Romario");
        spartanPojo.setGender("Male");
        spartanPojo.setPhone(3334545265l);

        Response response =  given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartanPojo)
                .when().post("/api/spartans/");

        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
    }
       @Test
        public void GetREquestWithPOJO(){

           Response response1 = given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().pathParam("id", 141)
                .when().get("/api/spartans/{id}");

        Spartan spartanResponse = response1.as(Spartan.class);
        System.out.println("spartanResponse = " + spartanResponse.toString());

    }
    @Test
    public void PutRequest(){

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Hasan");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 44445757567l);


        Response response =  given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 177)
                .body(requestMap)
               .when().put("/api/spartans/{id}");


    }
}
