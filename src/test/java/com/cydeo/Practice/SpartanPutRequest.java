package com.cydeo.Practice;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class SpartanPutRequest {

        @BeforeAll
        public static void setUpClass(){

            baseURI = "http://54.88.101.116:8000";
        }
        @Test
        public void PutRequest(){
        // using map
            Map<String, Object> putMap = new HashMap<>();
            putMap.put("name", "Tariko");
            putMap.put("gender", "Male");
            putMap.put("phone", 33565757567l);

            // we gonna send request body with updated value, and content type header
         Response response =   given().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                 .and().pathParam("id", 126)
                    .body(putMap).log().all()
                    .when().put("/api/spartans/{id}");

        response.prettyPrint();
}

    @Test
    public void PatchRequest(){
        // using map
        Map<String, Object> putMap = new HashMap<>();
        putMap.put("name", "Faruk");


        // we gonna send request body with updated value, and content type header
        Response response =   given().accept(ContentType.JSON).and().contentType(ContentType.JSON) // Hey api, I am sending JSON body
                .and().pathParam("id", 126)
                .body(putMap).log().all()
                .when().patch("/api/spartans/{id}");



             Response response1 =   given().accept(ContentType.JSON).pathParam("id", 126)
                        .when().get("/api/spartans/{id}");

        JsonPath jsonPath = response1.jsonPath();
        int id1 = jsonPath.getInt("id");
        String name= jsonPath.getString("name");
        String gender= jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        System.out.println(id1);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);



    }
    }


