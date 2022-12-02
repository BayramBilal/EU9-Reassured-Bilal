package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class BookitParametrizedTest {



    public static List<Map<String, String>> getExcelData(){
        ExcelUtil bookFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");

        return bookFile.getDataList();


    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void bookItTest(Map<String, String> user){

        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));


        given().accept(ContentType.JSON)
                .baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .queryParams(user)
                .when().get("/sign")
                .then().statusCode(200)
                .log().body();
    }



}
