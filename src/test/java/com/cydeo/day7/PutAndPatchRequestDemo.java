package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName ("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "Nihal");
        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 3221778899l);

        given().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                .body(putRequestMap).log().all()
                .and().pathParam("id", 126)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",126).log().all()
                .when().get("/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("Nihal", response.path("name"));
        assertEquals("Female", response.path("gender"));




    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("phone", 5566778843l);
        putRequestMap.put("name", "Hilal");

        given().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                .body(putRequestMap).log().all()
                .and().pathParam("id", 129)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send
//        Response response = given().accept(ContentType.JSON)
//                .and().pathParam("id",129)
//                .when().get("/api/spartans/{id}");
//
//        assertEquals(200,response.statusCode());
//        assertEquals("application/json",response.contentType());
//        assertEquals("Hilal", response.path("name"));

//        System.out.println(response.path("name").toString());
//        System.out.println(response.path("phone").toString());
//        System.out.println(response.path("gender").toString());
    }
    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){

        int idToDelete = 127;

          given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
        //send a get request after you delete make sure you are getting 404

//        Response response = given().accept(ContentType.JSON)
//                .and().pathParam("id",127)
//                .when().get("/api/spartans/{id}");
//
//        assertEquals(404,response.statusCode());
    }

}
