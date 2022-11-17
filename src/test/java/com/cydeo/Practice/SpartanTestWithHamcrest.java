package com.cydeo.Practice;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.response.Response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;



public class SpartanTestWithHamcrest {


    @BeforeAll
    public static void setUpClass(){

       baseURI = "http://54.88.101.116:8000";

    }

    @Test
    public void test1(){
        //request
        RestAssured.given().accept(ContentType.JSON)
                   .and().pathParam("id", 15)
                   .when().get(baseURI + "/api/spartans/{id}")

                            //response
                   .then().statusCode(200).and()
                            .assertThat().contentType("application/json");

}

    @Test
    public void test2(){
        //request
        RestAssured.given().accept(ContentType.JSON)
                        .and().pathParam("id", 15)
                        .when().get(baseURI + "/api/spartans/{id}")
                        .then().statusCode(200).and()
                        .assertThat().contentType("application/json")
                        .assertThat().body("id", equalTo(15), "name",is("Meta"),
                        "gender", is("Female"), "phone", is(1938695106));


}
}