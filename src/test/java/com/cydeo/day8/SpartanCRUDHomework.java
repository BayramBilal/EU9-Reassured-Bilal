package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;



public class SpartanCRUDHomework extends SpartanAuthTestBase {

     /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */

    @DisplayName("POST with Map to JSON")
    @Test
    public void PostWithMethodOfMap(){

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Nihal");
        requestJsonMap.put("gender", "Female");
        requestJsonMap.put("phone", 5455259095l);

        Response response =  given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        // verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(), is("application/json"));


        String  expectedResponseMessage ="A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"),is("Nihal"));
        assertThat(response.path("data.gender"),is("Female"));
       assertThat(response.path("data.phone"),is(5455259095l));
        System.out.println("response.statusCode() = " + response.statusCode());


        response.prettyPrint();
    }

    @DisplayName("PUT with Map to JSON")
    @Test
    public void PutAsAdmin(){


        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "Nihalim");
        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 3221778888l);

        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                .body(putRequestMap).log().all()
                .and().pathParam("id", 121)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

    }
    @DisplayName("Patch with Map to JSON")
    @Test
    public void PatchAsAdmin(){

        Map<String,Object> patchRequestMap = new HashMap<>();
        patchRequestMap.put("phone", 5566778843l);
        patchRequestMap.put("name", "Hilal");


        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                .body(patchRequestMap).log().all()
                .and().pathParam("id", 121)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);


    }

    @DisplayName("Patch with Map to JSON")
    @Test
    public void DeleteAsUser(){

//            given().auth().basic("user", "user")
//           given().auth().basic("editor", "editor")
           given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON).and().contentType(ContentType.JSON).log().all() // Hey api, I am sending JSON body
               .and().pathParam("id", 121)
                .when().delete("/api/spartans/{id}")
//                .then().statusCode(403).log().all();
                .then().statusCode(204).log().all();


    }


}




