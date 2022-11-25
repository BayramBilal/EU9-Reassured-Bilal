package com.cydeo.Practice;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.asserts.Assertion;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestPOJODeserialization {

    @BeforeAll
    public static void setUpClass(){

        baseURI = "http://54.88.101.116:8000";
    }
    @Test
    public void test1(){
        Response response =  given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");


        // how to convert json response to our spartan class

    SpartanPojo spartan1 = response.body().as(SpartanPojo.class);


    System.out.println(spartan1.toString());
    System.out.println(spartan1.getId());
    System.out.println(spartan1.getName());
        System.out.println(spartan1.getGender());
        System.out.println(spartan1.getPhone());
        assertEquals(spartan1.getName(),"Meta");
        assertEquals(spartan1.getId(),15);
        assertEquals(spartan1.getGender(),"Female");
        assertEquals(spartan1.getPhone(),1938695106l);

        spartan1.setGender("male");

        System.out.println(spartan1.getGender());


    }

    @Test
    public void gSonExample(){

        Gson gson = new Gson();

        String mrJsonBody = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
        // using gson method to de-serialize our json body

        SpartanPojo spartanMeta = gson.fromJson(mrJsonBody,SpartanPojo.class);

        System.out.println("spartanMeta.toString() = " + spartanMeta.toString());

        // serialization java object ----> JSON Body

        SpartanPojo spartan = new SpartanPojo(101, "Ali", "Male", 12345678l);


        // converting custom class to json (serialization)

        String  jsonBody = gson.toJson(spartan);
        System.out.println(spartan);


    }

}