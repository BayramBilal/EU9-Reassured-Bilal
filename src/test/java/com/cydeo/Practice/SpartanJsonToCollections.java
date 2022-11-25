package com.cydeo.Practice;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;

public class SpartanJsonToCollections {
/*
 "id": 11,
    "name": "Nona",
    "gender": "Female",
    "phone": 7959094216
 */
    @BeforeAll
    public static void setUpClass(){

        baseURI = "http://54.88.101.116:8000";
    }
    @Test
    public void test1(){
     Response response =  given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        // convert json response to java collections-Map

       Map<String, Object> spartanMAp =response.body().as(Map.class);
        System.out.println("spartanMAp.get(\"name\") = " + spartanMAp.get("name"));
        System.out.println("spartanMAp.get(\"id\") = " + spartanMAp.get("id"));

        Assertions.assertEquals("Nona", spartanMAp.get("name"));


    }
    @Test
    public void test2(){
        Response response =  given().accept(ContentType.JSON)
                           .when().get("/api/spartans");
        // convert full json body to List Of Maps
      List<Map<String, Object>> listofSpartanMap =response.body().as(List.class);

      // print all data of 1st spartan

        System.out.println("listofSpartanMap.get(0) = " + listofSpartanMap.get(0));
        Map<String, Object> firstSpartan=listofSpartanMap.get(0);
        System.out.println(firstSpartan.get("name"));

        for (Map<String, Object> eachMap : listofSpartanMap) {
            int counter=1;
            System.out.println(counter+ " - each Spartan = " + eachMap);
                    counter++;

        }

        }


}
