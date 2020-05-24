package com.automation.tests.day5;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POJOPractice {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

    }

    @Test
    public void getUser(){
        Response response = given()
                            .auth()
                             .basic       ("admin","admin")
                            .when()
                            .get("/spartans/{id}", 393).prettyPeek();

        //Get the body and map it to a Java object
        //For JSON response this requires that you have either Jackson or Gson
        //this is a deserialization
        //as() --> coming from RestAssured
        Spartan spartan = response.as(Spartan.class);
        //deserialization: POJO <-- JSON
        //serialization : POJO --> JSON
        // both operations are done with a help of Gson
        //RestAssured automatically calls Gson for these operations
        System.out.println(spartan);

        //also can be stored in Map as a key-value
        Map<String, ?> spartanAsMap = response.as(Map.class);
        System.out.println(spartanAsMap);

        assertEquals(393,spartan.getId());
        assertEquals("Michael Scott", spartan.getName());

    }

    @Test
    public void addUser(){

        //we can post 1 user at a time

        Spartan spartan = new Spartan("Nora", "Female",8888888888L);
        Gson gson = new Gson();
        String pojoAsJSON = gson.toJson(spartan);
        System.out.println(pojoAsJSON);

        Response response = given()
                            .auth()
                            .basic("admin","admin")
                            .contentType(ContentType.JSON)
                            .body(spartan) // this how we specify a Gson body
                            .when()
                            .post("/spartans").prettyPeek();
        response.then().statusCode(201); // to ensure that user was created

        
        int userId = response.jsonPath().getInt("data.id");

        System.out.println("userId = " + userId);

        System.out.println("@@@@How to delete user!@@@@@");


        given().auth().basic("admin","admin")
                .when().delete("/spartan/{id}", userId).prettyPeek()
                .then().assertThat().statusCode(204); // to ensure that user is deleted

    }


}
