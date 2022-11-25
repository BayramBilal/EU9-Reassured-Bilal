package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanWithAuthTests extends SpartanAuthTestBase {


    @DisplayName ("GET /api/spartans as a public user(guest) expect 401 ")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(401)
                .log().all();


}

    @DisplayName ("GET /api/spartans as a admin user expect 200")
    @Test
    public void testAdmin(){

    // how to pass admin as a username and password ?
        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .log().all();
    }
    @DisplayName ("Delete /api/spartans/{id} as a editor user expect 403")
    @Test
    public void testEditorDelete(){

        given().auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", "101")
                .when().get("/api/spartans/{id}")
                .then().statusCode(403)
                .log().body();
}


 }