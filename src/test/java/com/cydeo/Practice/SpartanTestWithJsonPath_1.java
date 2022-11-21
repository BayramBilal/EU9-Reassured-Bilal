package com.cydeo.Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTestWithJsonPath_1 {

    @BeforeAll
    public static void setUpClass(){

        RestAssured.baseURI = "http://54.88.101.116:8000";

    }

    @Test
    public void JsonPath1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get(baseURI + "/api/spartans/{id}");


        assertEquals(200, response.statusCode());
        int id = response.path("id");
        System.out.println("id = " + id);

       assertEquals("application/json", response.contentType());

       // how to read value with JsonPath ?
        JsonPath jsonData = response.jsonPath();
        int id1 = jsonData.getInt("id");
        String name= jsonData.getString("name");
        String gender= jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        // verify json payload with JsonPath

        assertEquals(11, id);
        assertEquals("Nona", name);
        assertEquals("Female", gender);
        assertEquals(7959094216l, phone);












    }

}
