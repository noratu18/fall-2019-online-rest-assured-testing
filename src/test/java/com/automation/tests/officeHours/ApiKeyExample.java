package com.automation.tests.officeHours;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiKeyExample {

    @BeforeAll
    public static void setup(){

        RestAssured.baseURI = "https://www.zipcodeapi.com/";
    }

    /*
    get distance between 2 zip codes
    verify status code 200
    verify distance field not empty
    // /rest:api_key/distance.json/:zip_code1/:zip_code2/:units
     */

    @Test
    public void testDistance(){
        given()
                .pathParam("api_key", "dGNHANfPybf4A5nvV7eVODmRlGd3aH86RTNq5OQ3mBuUkf5nDC0bI1rte6Yh1rdm")
                .pathParam("zip_code1", "20005")
                .pathParam("zip_code2", "20001")
                .pathParam("units", "miles")
        .when()
                //here we creating url --> pathParam has to be inside the {}
                // or we can pass as pathparam like 20005,20001 and miles
                .get("/rest/{api_key}/distance.json/{zip_code1}/{zip_code2}/{units}")
                .prettyPeek()
        .then()
                .statusCode(200)
                .body("distance", not(emptyOrNullString()));//this one assert check for both options
    }

    /*
    test zipcode 02184
    verify city Braintree
     */
    @Test
    public void testMB(){
     //   /rest:api_key/.json/:zip_code/:units
        given()
                .pathParam("api_key", "dGNHANfPybf4A5nvV7eVODmRlGd3aH86RTNq5OQ3mBuUkf5nDC0bI1rte6Yh1rdm")
                .pathParam("zip_code", "02184")
                .pathParam("units", "degrees")
        .when()
                .log().all()
                .get("/rest/{api_key}/info.json/{zip_code}/{units}")
                .prettyPeek()
        .then()
                .assertThat().statusCode(is(200))
        .and()
                .assertThat().body("city", is("Braintree"));

    }
}
