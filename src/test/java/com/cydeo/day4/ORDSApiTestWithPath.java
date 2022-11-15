package com.cydeo.day4;


import com.cydeo.utilities.HRTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request with Path method")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when().get("/countries");


    assertEquals(200, response.statusCode());

    // print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        // print hasMOre
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        // print first Country Id

        String countryId =  response.path("items[0].country_id");
        System.out.println("countryId = " + countryId);
        // second Country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));

        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);
                
                // get me all country names

        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        // asser all regions id = 2

        List<Integer> allRegionsIds = response.path("items.region_id");
        for (Integer allRegionsId : allRegionsIds) {
            System.out.println("allRegionsId = " + allRegionsId);
            assertEquals(2, allRegionsId);
        }
    }


    @DisplayName ("GET request to /employees with query param")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .log().all()
                .when().get("/employees");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));

       List<String> allJobdIds = response.path("items.job_id");

        for (String JobdId : allJobdIds) {

            System.out.println("JobdId = " + JobdId);

            assertEquals("IT_PROG", JobdId);



        }
// TASK print each name of IT POG
        JsonPath jsonPath = response.jsonPath();

        List<String> firstNameWithJobId_ITPROG = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("firstNameWithJobId_ITPROG = " + firstNameWithJobId_ITPROG);

        }
    }







