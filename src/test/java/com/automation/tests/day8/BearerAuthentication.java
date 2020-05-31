package com.automation.tests.day8;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class BearerAuthentication {

    @BeforeAll
    public static void setup(){
    baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";

    }

    @Test
    public void loginTest(){
        Response response = given()
                .queryParam("email", "teacherva5@gmail.com")
                .queryParam("password", "maxpayne")
                .when()
                .get("/sign").prettyPeek();
        
        String token = response.jsonPath().getString("accessToken");
        System.out.println("token = " + token);

    }

    @Test
    @DisplayName("Negative test: attempt to retrieve list of rooms without authentication token")
    public void getRoomsTest(){
        // 422 ok, because anyways we didn't get data
        // but, we supposed to get 401 status code
        get("/api/rooms").prettyPeek()
                .then().statusCode(401);

    }

    @Test
    public void getRoomsTest2(){
        // 1st step: Request to get a token
        Response response = given()
                .queryParam("email", "teacherva5@gmail.com")
                .queryParam("password", "maxpayne")
                .when()
                .get("/sign");
        response.then()
                .log().ifError();

        // 2nd step: catch the response and put into variable to use it later
        String token = response.jsonPath().getString("accessToken");

        // 3rd step: get the token and use for other tests
        Response response1 = given()
                                    .auth().oauth2(token)
                             .when()
                                    .get("/api/rooms").prettyPeek();

    }

    @Test
    public void getAllTeamsTest(){
        Response response = given()
                .header("Authorization", "B```````earer " + getToken())
                .when()
                .get("/api/teams").prettyPeek();

        response.then().statusCode(200);

    }


    /**
     * this methods will generate tokens, so we don't need to generate again and again
     * @return
     */
    public String getToken() {
        // 1st step: Request to get a token
        Response response = given()
                .queryParam("email", "teacherva5@gmail.com")
                .queryParam("password", "maxpayne")
                .when()
                .get("/sign");
        response.then()
                .log().ifError();

        // 2nd step: catch the response and put into variable to use it later
        String token = response.jsonPath().getString("accessToken");
        System.out.println("token = " + token);
        return token;

    }



    public String getToken(String email, String password) {
        // 1st step: Request to get a token
        Response response = given()
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign");
        response.then()
                .log().ifError();

        // 2nd step: catch the response and put into variable to use it later
        String token = response.jsonPath().getString("accessToken");
        System.out.println("token = " + token);
        return token;

    }


}
