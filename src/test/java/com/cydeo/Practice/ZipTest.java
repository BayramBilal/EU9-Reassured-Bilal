package com.cydeo.Practice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipTest {
    @BeforeEach
    public void testSetUp(){

        baseURI ="http://www.zippopotam.us/";

    }

    @Test
    public void ZipTest2(){
        Response response = given().accept(ContentType.JSON)
                .get("us/90210");

//        response.prettyPrint();

        Map<String, Object> responseMap = response.as(Map.class);

        System.out.println("responseMap = " + responseMap);

        List<Map<String, Object>> places = (List<Map<String, Object>>) responseMap.get("places");
        String actualState = (String) places.get(0).get("state");
        System.out.println("actualState = " + actualState);


        JsonPath jsonPath = response.jsonPath();

       String places2 = jsonPath.getString("places[0].state");
        System.out.println(places2);

    }
}