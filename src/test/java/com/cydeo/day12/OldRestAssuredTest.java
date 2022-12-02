package com.cydeo.day12;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class OldRestAssuredTest extends SpartanNewBase {


    @Test
    public void getAllSpartans(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .log().all()
                .when().get("/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .log().all();


    }


}
