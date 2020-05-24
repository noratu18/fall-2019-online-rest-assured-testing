package com.automation.tests.day4;

import com.automation.utilities.ConfigurationReader;
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

public class ORDSTestsDay4 {

@BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("ORDS.URI");

    }
    /**
     * Warmup!
     * Given accept type is JSON
     * When users sends a GET request to “/employees”
     * Then status code is 200
     * And Content type is application/json
     * And response time is less than 3 seconds
     */

    @Test
    @DisplayName("Verify status code, content type and response time")
    public void verifyResponseTime() {
        given()
                //    .contentType(ContentType.JSON)//this for put, post
                .accept(ContentType.JSON) // this for retrieve data, receive
                .when()
                .get("/employees").prettyPeek()

                .then()
                .assertThat().statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS);


    }

    /**
     *
     Given accept type is JSON
     And parameters: q = {"country_id":"US"}
     When users sends a GET request to "/countries"
     Then status code is 200
     And Content type is application/json
     *
     */

    @Test
    @DisplayName("Verify country name, content type and status code for country with ID US")
    public void verifyCountriesTest1(){

        given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"country_id\":\"US\"}")
                .when()
                .get("/countries")
                .then().assertThat().statusCode(200
        )
                .contentType(ContentType.JSON)
                .body("items[0].country_name", is ("United States of America"));

        //SECOND REQUEST
        //accept(ContentType.JSON) --> to request JSON from the web service
        Response response = given()
                            .accept(ContentType.JSON)
                .when()
                .get("/countries").prettyPeek();

        //it --> coming from JPath
        String countryName = response.jsonPath().getString("items.find{it.country_id == 'US'}.country_name");
        Map<String, Object> countryUS = response.jsonPath().get("items.find{it.country_id == 'US'}");
        //find all country names from region 2 
        //collectionName.find{it.propertyName == 'Value' } --> to get collection objects where property equals to some value
        //collectionName.find{it.propertyName == 'Value'} --> to object where  property equals to some value

        //to get collection properties where property where property equals to some value
        //collectionName.findAll{it.propertyName == 'Value'}.propertyName
        List<String> countryNames = response.jsonPath().getList("items.findAll{it.region_id == 2}.country_name");
        

        System.out.println("countryName = " + countryName);
        System.out.println("countryUS = " + countryUS);
        System.out.println("countryNames = " + countryNames);

        for(Map.Entry<String, Object> entry : countryUS.entrySet()){
            System.out.printf("key = %s, value =%s\n", entry.getKey(), entry.getValue());

        }
    }


    //let's find employee with highest salary, use GPath
    @Test
    public void getEmployeeTest(){
        Response response = when()
                            .get("/employees").prettyPeek();
        //CollectionName.nax{it.propertyName}
        Map<String, ?> bestEmlpoyee =response.jsonPath().get("items.max{it.salary}");
        Map<String, ?> poorGuy = response.jsonPath().get("items.min{it.salary}");

        //these methods are coming from Groovy
        // http://docs.groovy-lang.org/latest/html/documentation/#_gpath
        int companiesPayroll = response.jsonPath().get("items.collect{it.salary}.sum()");
        
        System.out.println("bestEmlpoyee = " + bestEmlpoyee);
        System.out.println("poorGuy = " + poorGuy);
        System.out.println("companiesPayroll = " + companiesPayroll);


    }

    /**
     * given path parameter is “/employees”
     * when user makes get request
     * then assert that status code is 200
     * Then user verifies that every employee has positive salary
     *
     */

    @Test
    @DisplayName("Verify that every employee has positive salary")
    public void testSalary(){
        when()
                .get("/employees")
                .then().assertThat().statusCode(200)
                .body("items.salary", everyItem(greaterThan(0)))
                .log().ifError();

        //log().ifError() --> process of recording, some information log of request is pretty much the body, header
        //it will be logged if there is an error.
        //if there is an error, it will print log.

        //everyItem(), greaterThan() --> coming from Matcher class


    }


    /**
     * given path parameter is “/employees/{id}”
     * and path parameter is 101
     * when user makes get request
     * then assert that status code is 200
     * and verifies that phone number is 515-123-4568
     *
     */

    @Test
    public void verifyPhoneNumber(){

        //So we use Response here to save the it (the response)
        // because we need to pick apart the data in the body?
        Response response = when()
                            .get("/employees/{id}",101)
                            .prettyPeek();

        String expected = "515-123-4568";
        String actual = response.jsonPath().getString("phone_number")
                        .replace(".", "-");

        assertEquals(200, response.statusCode());
        assertEquals(expected,actual);
    }

}
