package com.automation.tests.day7;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class APIKey {

    private final String API_Key = "29055371";


    @BeforeAll
    public static void setup(){
        baseURI = "http://omdbapi.com/"; // this is endpoint

    }

    @Test
    public void getMovieTest(){


        String itemToSearch = "Frozen";

        //sending request
        Response response = given()
                .queryParam("t", itemToSearch)
                .queryParam("apikey", API_Key)
                .when()
                .get().prettyPeek();

        //getting request
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("Title", containsString(itemToSearch));


        List<Map<String, String>> ratings =response.jsonPath().get("Ratings");
             System.out.print("ratings = " + ratings);

    }

    @Test
    public void authenticationTest(){

        String itemToSearch = "Frozen";
        //sending request
        Response response = given()
                .queryParam("t", itemToSearch)
                .when()
                .get().prettyPeek();

        //getting request
        response
                .then()
                .assertThat()
                .statusCode(401)
                .body("Title", containsString(itemToSearch));


    }



}
