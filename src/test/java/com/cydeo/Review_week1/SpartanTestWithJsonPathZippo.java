package com.cydeo.Review_week1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTestWithJsonPathZippo {

    @BeforeAll
    public static void setUpClass(){

        RestAssured.baseURI = "https://www.zippopotam.us";

    }

    @Test
    public void JsonPath1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                     .when().get(baseURI + "/us/90210");


        assertEquals(200, response.statusCode());

       assertEquals("application/json", response.contentType());


        JsonPath jsonData = response.jsonPath();

        String countryName= jsonData.getString("country");
        String state= jsonData.getString("places[0].state");

        System.out.println("state = " + state);
        System.out.println("countryName = " + countryName);


        // verify json payload with JsonPath

        assertEquals("United States", countryName);
        assertEquals("California", state );

    }
    @Test
    public void JsonPath2(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURI + "/us/ma/belmont");


        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        response.prettyPrint();


      JsonPath jsonPath = response.jsonPath();

      String placeName= jsonPath.getString("places[0].'place name'");
      int postalCode = jsonPath.getInt("places[1].'post code'");
        System.out.println(jsonPath.getDouble("places[0].longitude"));
        System.out.println("placeName = " + placeName);
        System.out.println("postalCode = " + postalCode);


        assertEquals("Belmont", placeName);
        //assertEquals("02478", postalCode);

    }

    @Test
    public void JsonPath3(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURI + "/us/va/fairfax");


      response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();

//        double longitude= jsonPath.getDouble("places[0].longitude");
//        double latitude = jsonPath.getDouble("places[0].latitude");
//
//        System.out.println("latitude = " + latitude);
//        System.out.println("longitude = " + longitude);

        System.out.println(jsonPath.getList("places"));


//        jsonPath.getList("findAll{it.longitude==-77.2888}");
        jsonPath.getList("places.findAll{it.latitude==38.8318}.longitude");



    }
}