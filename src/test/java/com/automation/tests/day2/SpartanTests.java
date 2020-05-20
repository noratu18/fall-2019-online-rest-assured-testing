package com.automation.tests.day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class SpartanTests {

    String BASE_URL = "http://54.208.129.36:8000";

    //URL (Uniform Resource Identifier) --> URL + URN = http://www.google.com/index.html
    //URI (Uniform Resource Locator) -->  http://google.com
    //URN (Uniform Resource Name ) -->  /index.html

    @Test
    @DisplayName("Get list of all spartans") //optional to use @DisplayName
    public void getAllSpartans(){

                //401 --> unauthorized, since we didn't provide credentials request failed
                // how to provide credentials
                //there different types of authentication: basic, oauth 1.0, oauth 2.0, api key, bearer token, etc...
                //spartan requires basic authentication: username and password
            given().
                    auth().basic("admin", "admin").
                    baseUri(BASE_URL).
                when().
                    get("/api/spartans").prettyPeek().
                then().statusCode(200);
    }


    /*
    how to add new spartan

     */

    @Test
    @DisplayName("Adding new spartan")
    public void addSpartan(){
        //JSON supports different data types: string, integer, boolean
        String body = "{\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"name\": \"Sayanora\",\n" +
                "  \"phone\": 1818181818\n" +
                "}";

        //instead of string variable, we can use external JSON file
        // use File class to read JSON and pass it into body
        //provide path to the JSOn as a parameter
        File jsonFile = new File(System.getProperty("user.dir") + "/spartan.json");

        //to create new item , we perform POST request
        given().
                contentType(ContentType.JSON).
                auth().basic("admin","admin").
                body(jsonFile).
                baseUri(BASE_URL).
       when().
                post("/api/spartans").prettyPeek().
       then().
                statusCode(201);

    }

    @Test
    @DisplayName("deleting some spartan")
    public void deleteSpartanTest(){
        //{id} --> path parameter
        //YOU CANNOT DELETE SOMETHING TWICE
        //we use delete() method ro delete something
        //204--> No content, most common status code for successful delete action
        //authentication -=-> who you are? You need to tell to the server who you are brfore getting any data
        // ALL HTTP
        // 201--> always after successful POST request
        // 200 --> always after successful GET request
        // 204 --> always after successful DELETE request
        // 4xx -->  always after unsuccessfully request and it was  YOUR FAULT
        given().
                auth().basic("admin", "admin").
                baseUri(BASE_URL).
        when().
                delete("/api/spartans/{id}", 701).prettyPeek().
        then().
                statusCode(204);

    }


}
