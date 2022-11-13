package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath {


    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.88.101.116:8000";

}

    /*
        Given accept type is json
        And path param id is 10
        When user sends a get request to "api/spartans/{id}"
        Then status code is 200
        And content-type is "application/json"
        And response payload values match the following:
             id is 10,
             name is "Lorenza",
             gender is "Female",
             phone is 3312820936
      */
    @DisplayName ("GET one spartan with path method")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 10 )
                .log().all()
                .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

//        assertTrue(response.body().asString().contains("10"));
//        assertTrue(response.body().asString().contains("Lorenza"));
//        assertTrue(response.body().asString().contains("Female"));
//        assertTrue(response.body().asString().contains("3312820936"));


        //verify using path method
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id =  response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

       // assert values

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);


}
    @DisplayName("GET all spartan and navigate with path()")
    @Test
    public void test2(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans");

//               response.prettyPrint();

        int firstId= response.path("id[0]");
        System.out.println("firstId = " + firstId);
        String name = response.path("name[0]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);


        List<String> names = response.path("name");
        System.out.println(names);

        for (String n : names) {
            System.out.println(n);

        }
    }
}