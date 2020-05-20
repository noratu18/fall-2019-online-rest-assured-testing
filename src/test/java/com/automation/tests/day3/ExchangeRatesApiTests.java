package com.automation.tests.day3;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
//used for advanced assertions

public class ExchangeRatesApiTests {

    /**
     * * <p>{@code @BeforeAll} methods must have a {@code void} return type,
     * * must not be {@code private}, and must be {@code static} by default.
     */

    @BeforeAll // must be static and void
    public static void setup(){
        //The base URI that's used by REST assured when making requests if a non-fully qualified URI is used in the request.
        //Default value is {@value #DEFAULT_URI}.
        //because we imported static RestAssered.*
        // we don't need to use RestAssured.baseURI
        //for every single request this is a base URI
        baseURI = "https://api.openrates.io";
    }

    //get latest currency rates
    @Test
    public void getLatestRates(){
        //after ? we specify query parameters. If there are coiple of them we use & to concatinate them
        //  / --> is path parameter
        //  ? -->  sorting/filter - it is query parameter
        //http://www.google.com/index.html?q=apple&zip=123123
        //q--> query parameter
        //zip--> another query parameter
        //with rest assured , we provide query parameters into given() part.
        //give() --> request preparation
        //you can specify query parameters in URI explicitly: http://api.openrates.io/latest?base=USD
        //
      //  Response response = get("/latest").prettyPeek();
        Response response = given()
                            .queryParam("base", "USD")
                            .when()
                            .get("/latest").prettyPeek();
        //prettyPeek() --> returns response
        //prettyPrint() --> returns String
        //given() - all input details
        //when() - submit the API
        //then() - validate the response

        //verify that GET request to the endpoint was successful
        //how to get header value
        //to read header of the response
        Headers headers = response.getHeaders();
         String contentType = headers.getValue("Content-Type");
        System.out.println("contentType = " + contentType);
        
        response.then().assertThat().statusCode(200);
        //is() --> same is equalsTo()
        //we can use contains() instead is()
        response.then().assertThat().body("base", is("USD"));

        //let's verify that response contains today's date
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().assertThat().body("date", containsString(date));

    }

    //get history of rates for 2008
    @Test
    public void getHistoryOfRates(){
        Response response = given()
                            .queryParam("base", "USD")
                            .when()
                            .get("/2008-01-02").prettyPeek();
        
        Headers headers = response.getHeaders(); // response header
       // System.out.println("headers = " + headers);

        response.then().assertThat()
                        .statusCode(200)
                        .and()
                        .body("date", is("2008-01-02"))
                        .and()
                        .body("rates.USD", equalTo(1.0f) );
        //and() doesn't have a functional role, it's just a syntax sugar
        // we can chain validations
        //how we can retrieve

        //rates--> it's an object
        //all currencies are like instance variables
        //to get any instance variable (property), objectName.propertyName
        float actual = response.jsonPath().get("rates.USD");
        //we use json.file
        assertEquals(1.0, actual);
        System.out.println("actual parameter = " + actual);

    }
    /**
     *  Get a JsonPath view of the response body. This will let you use the JsonPath syntax to get values from the response.
     *      * Example:
     *      * <p>
     *      * Assume that the GET request (to <tt>http://localhost:8080/lotto</tt>) returns JSON as:
     *      * <pre>
     *      * {
     *      * "lotto":{
     *      *   "lottoId":5,
     *      *   "winning-numbers":[2,45,34,23,7,5,3],
     *      *   "winners":[{
     *      *     "winnerId":23,
     *      *     "numbers":[2,45,34,23,3,5]
     *      *   },{
     *      *     "winnerId":54,
     *      *     "numbers":[52,3,12,11,18,22]
     *      *   }]
     *      *  }
     *      * }
     *      * </pre>
     *      * </p>
     *      * You can the make the request and get the winner id's by using JsonPath:
     *      * <pre>
     *      * List<Integer> winnerIds = get("/lotto").jsonPath().getList("lotto.winnders.winnerId");
     *      * </pre>
     *
     */






}
