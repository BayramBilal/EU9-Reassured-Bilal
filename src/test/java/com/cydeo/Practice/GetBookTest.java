package com.cydeo.Practice;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class GetBookTest {

    @BeforeEach
    public void testSetUp(){

     baseURI ="https://demoqa.com";

    }
    @Test
    public void bookTest1(){
        Response response = given().accept(ContentType.JSON)
                .get("/BookStore/v1/Books");

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertTrue(response.contentType().contains("json"));

        JsonPath jsonPath=response.jsonPath();
       List<Map<String, Object>> books = jsonPath.getList("books");
        System.out.println("books = " + books);
        System.out.println("books.get(1) = " + books.get(1));
        System.out.println("jsonPath.get(\"books[0].title\") = " + jsonPath.get("books[0].title"));

        String expectedAuthor ="Addy Osmani";
        String actualAuthor = jsonPath.getString("books[1].author");
        String actualAuthor1 = (String) books.get(1).get("author");

        assertEquals(expectedAuthor, actualAuthor);
        assertEquals(expectedAuthor, actualAuthor1);
        System.out.println("actualAuthor1 = " + actualAuthor1);
        System.out.println("actualAuthor = " + actualAuthor);
        System.out.println("expectedAuthor = " + expectedAuthor);

        Map<String, Object> books2 = response.body().as(Map.class);  // as method

        System.out.println("books2 = " + books2);

        String actualTitle = response.path("books[0].title");

        assertEquals("Git Pocket Guide", actualTitle);
        System.out.println("actualTitle = " + actualTitle);





    }










}
