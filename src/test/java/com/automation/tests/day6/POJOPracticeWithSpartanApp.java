package com.automation.tests.day6;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POJOPracticeWithSpartanApp {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication = basic("admin", "admin");

    }

    @Test
    public void addSpartanTest(){
        Map<String, Object> spartan = new HashMap<>();
        spartan.put("gender", "Male");
        spartan.put("name", "Meder");
        spartan.put("phone", "888188181818");

        //Interface
        // Allows you to specify how the request will look like.
        RequestSpecification requestSpecification = given()
                                            //    .auth().basic("admin", "admin")
                                                .contentType(ContentType.JSON)
                                                .accept(ContentType.JSON)
                                                .body(spartan);
        //this translation is done with GSON


        Response response = given()
                     //       .auth().basic("admin", "admin")
                            .contentType(ContentType.JSON)
                            .body(spartan)
                            .when()
                            .post("/spartans").prettyPeek();

        response.then().statusCode(201);
        response.then().body("success", is("A Spartan is Born!"));



        //how can we store spartan response as an object?
        // deserialization
        Spartan spartanResponse = response.jsonPath().getObject("data", Spartan.class);

        // spartanResponse is a Spartan
        System.out.println(spartanResponse instanceof Spartan); // must be true

    }

    @Test
    public void updateSpartanTest(){
        int userToUpdate = 178;
        String name = "Baha";

        //HTTP PUT--> request to update exiting record, for example existing spartan.
        // PUT --> requires to provide ALL parameters in body
        // PUT --> requires same body as POST , we need to give whole entire body
        // If you miss at least one parameter, it will not work

        Spartan spartan = new Spartan(name, "Female", 1234658790L);

        //get spartan from web service
        Spartan spartanToUpdate = given()
                            //    .auth().basic("admin", "admin")
                                .accept(ContentType.JSON)
                                .when()
                                .get("/spartans/{id}", userToUpdate).as(Spartan.class);
        //update property that you need without affecting after properties
        System.out.println("Before update: " + spartanToUpdate);
        spartanToUpdate.setName(name); // to change only name

        //request to update existing user with id 127
                Response response = given()
                              //      .auth().basic("admin", "admin")
                                    .contentType(ContentType.JSON)
                                    .body(spartanToUpdate)
                                    .when()
                                    .put("/spartans/{id}", userToUpdate).prettyPeek();

            //verify that status code is 204 after update
                response.then().statusCode(204);
        System.out.println("=================================");

        //to get user with id 127, the one that we've just updated
                given().
                        //.auth().basic("admin", "admin").
                when().get("/spartans/{id}", userToUpdate).prettyPeek()
                            .then().statusCode(200)
                            //to get back the change
                           .body("name", is(name));
                //verify that name is correct, after update


    }

    @Test
    @DisplayName("Verify that user can perform PATCH request")
    public void patchUserTest1(){
        // PATCH --> partial update of existing record, we don't need to give the whole body

        int userId = 212; // user to update, make user with this id exist

       //get all spartans with List
        Response response0 = given().accept(ContentType.JSON)
                                    .when().get("/spartans").prettyPeek();
        //I can save them all in the array list
        List<Spartan> allSpartans = response0.jsonPath()
                                    .getList("", Spartan.class); // to get the whole list
        //Spartan.class --> data type of collection, to create spartan object as a Json, for translation
        // getList --> get JSON body as a list

        //generates random number
        Random random = new Random();
        int randomNum = random.nextInt(allSpartans.size());

        int randomUserId = allSpartans.get(randomNum).getId();

        userId = randomUserId; // to assign random user Id
        System.out.println("Name before: " + allSpartans.get(randomNum).getName());
        System.out.println("Name after:" + allSpartans);

        Map<String, String> update = new HashMap<>();
        update.put("name", "Ainura");

        //this is a request to update user
        Response response = given()
                        .contentType(ContentType.JSON)
                        .body(update)
                        .when()
                        .patch("/spartans/{id}", userId).prettyPeek();

        response.then().assertThat().statusCode(204);

        //after we updated partially with PATCH request,
        // let's make sure that name is updated
        //this is a request to verify that name
        // was updated and status code is correct as well
        given().accept(ContentType.JSON)
                .when().get("/spartans/{id}", userId)
                .then().assertThat().statusCode(200)
                .body("name", is("Ainura")); //assertion HamcsrestMatcher


    }

}
