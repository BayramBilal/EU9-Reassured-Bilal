package com.cydeo.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParametrizedTestInJunit5 {


    @ParameterizedTest
    @ValueSource (ints = {1,3,5,6,7,9,10,5,21,32})
    public void testMultipleNumbers(int number){

        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);

    }

    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name){
        System.out.println("name = " + name);

    }


    @ParameterizedTest
    @ValueSource(strings = {"merve","nihal","ali","Ozgul"})
    public void testMultipleNames2(String name){
        System.out.println("name = " + name);

    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200


    @ParameterizedTest
    @ValueSource (ints = {22030, 22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipCodeTest(int zipCode){

        given()
                .baseUri("https://api.zippopotam.us") //we got zipcode from valueSource
                .pathParam("zipcode", zipCode)
                .and().log().all() // request log
                .when().get("/us/{zipcode}")
                .then()
                 .log().all() // response log
                .statusCode(200);


    }

    @ParameterizedTest
    @ValueSource (ints = {11510, 11512})
    public void idTest(int Id){

        given()
                .baseUri("https://cybertek-reservation-api-qa.herokuapp.com")
                .pathParam("id", Id)
                .and().log().all() // request log
                .when().get("/api/teachers/{id}")
                .then()
                .log().all() // response log
                .statusCode(200);


    }
}
