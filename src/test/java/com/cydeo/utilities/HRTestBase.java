package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {

    @BeforeAll
    public static void init(){

        baseURI = "http://54.88.101.116:1000/ords/hr";

        // get ip adress from configurations

    }
}
