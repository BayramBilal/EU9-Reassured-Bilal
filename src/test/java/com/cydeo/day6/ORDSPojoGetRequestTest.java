package com.cydeo.day6;

import com.cydeo.pojo.*;
import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.lang.model.SourceVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).log().body().extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

        Link link = region1.getLinks().get(0);
        System.out.println("link = " + link);

    }
    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet(){
        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);


        System.out.println("employee1 = " + employee1);




    }

    @Test
    public void zipCodeGet(){
        ZipCode zipCode1 = get("/locations").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", ZipCode.class);

        System.out.println("zipCode1 = " + zipCode1);
    }


    @Test
    public void countryGet(){
       CountryId countryId1 = get("/countries").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", CountryId.class);
        System.out.println("countryId1 = " + countryId1);


    }

    @Test
    public void regionsGet(){
        Regions regions1 = get("/regions").then().statusCode(200)
                .extract().response().as(Regions.class);

        assertThat(regions1.getCount(),is(4));
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        List<Region> items = regions1.getItems();

        for (Region region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());

        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        List<Integer>  expectedregionIds = Arrays.asList(1,2,3,4);
        List<String> expectedregionNames = Arrays.asList("Europe","Americas","Asia","Middle East and Africa");


  assertThat(regionIds,is(expectedregionIds));
  assertThat(regionNames,is(equalTo(expectedregionNames)));


    }
}
