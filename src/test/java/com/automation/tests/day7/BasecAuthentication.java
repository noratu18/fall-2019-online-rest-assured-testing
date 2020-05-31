package com.automation.tests.day7;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.automation.pojos.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.automation.utilities.ConfigurationReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasecAuthentication {

    @Test
    public void spartanAuthentication(){

        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

        // in the given part, we provide request specifications
        given().auth().basic("admin", "admin")
                .when()
                .get("/spartans").prettyPeek()
                .then().statusCode(200);

        //assertThat() --> is syntax sugar

    }

    @Test
    public void authorizationTest(){ // to check if it works with invalid authorization

        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        Spartan spartan = new Spartan("Nora", "Female", 4561237890L);

        // in the given part, we provide request specifications
        given().auth().basic("user", "user")
                .body(spartan)
                .contentType(ContentType.JSON)
                .when()
                .post("/spartans").prettyPeek()
                .then().statusCode(403);

            /*
            user--> doesn't have rights to add, delete or edit users. Only read
            admin--> has a permission to add new users
            403--> Forbidden access. You logged in, but you are trying to do something that you are not allowed.
            Authentication problem --> you didn't login
            Authorization problem--> you logged in but cannot do some actions.
             */

    }

    @Test
    @DisplayName("if we don't provide any credentials")
    public void authenticationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        //if don't provide credentials, we must get 401 status code
        get("/spartans").prettyPeek().then().statusCode(401);
    }

    @Test
    public void authenticationTest2(){

        baseURI = "http://practice.cybertekschool.com";

                given().auth().basic("admin","admin")
                        .when()
                            .get("/basic_auth").prettyPeek()
                            .then()
                            .statusCode(200)
                        .contentType(ContentType.HTML);
    }


}
