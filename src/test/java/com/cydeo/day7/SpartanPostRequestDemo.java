package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanPostRequestDemo extends SpartanTestBase {

  /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void PostMethod1(){

        String requestJsonBody = "  {\"name\": \"Severus\",\"gender\": \"Male\",\n" +
                                    "\"phone\": 8877445596}";

      Response response = given().accept(ContentType.JSON).and()
                    .contentType(ContentType.JSON)
                    .body((requestJsonBody))
                    .when()
                    .post("/api/spartans");


      // verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(), is("application/json"));


        String  expectedResponseMessage ="A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596l));



    }
    @DisplayName("POST with Map to JSON")
    @Test
    public void PostMethod2(){

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Severus");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 8877445596l);

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        // verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(), is("application/json"));


        String  expectedResponseMessage ="A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596l));




        response.prettyPrint();


    }
    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3(){
        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("SeverusSpartan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("SeverusSpartan"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();


    }
}
