package com.cydeo.utilities;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanTestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.88.101.116:8000";

        //get ip address from configurations
        String dbUrl = "jdbc:oracle:thin:@54.88.101.116:1521:xe";
      //  String dbUsername = "SP";
        //  String dbPassword = "SP";

      //  DBUtils.createConnection(dbUrl, dbUsername, dbPassword);


        String dbUsername = "hr";
        String dbPassword = "hr";

         // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){

       // DBUtils.destroy();
    }
}