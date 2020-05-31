package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSTests {

    /*
    //address of ORDS web service, that is running no AWS ec2.
    //dara is coming from SQL Oracle data base to this web service
    //during back-end testing with SQL developer and JDBC API
    //we were accessing data base directly
    //now, we gonna access web service

    //according to OOP conventions, all instance variable should be private
    //but, if we will make it public, it will not make ant difference for us
    //it's just good practice, so later we will not hesitate which keyword to use when it's gonna important
     */

    String BASE_URL = "http://3.90.112.152:1000/ords/hr";


    @Test
    @DisplayName("Get list of all employees")
    public void getAllEmployees() {
        /*
        //we start from given()
    //then we can specify type of request like: get(), put(), delete(), post()
    //and as parameter, we enter resource location (URI)
    //then we are getting response back. that response we can put into Response object
    //from response object, we can retrieve: body, header, status code
    //it works without RestAssured.given() because of static import
    //verify that status code is 200
         */
        //response can be saved in the Response object
        //prettyPeek() --> method that prints response info in nice format
        // also the method returns Response object
        //response contains body, header and status line
        // body (payload) --> contains content that we requested from the web service
        // header --> contains meta data
        Response response = given().baseUri(BASE_URL).//used for setup
                when().
                get("/employees").prettyPeek();//it lets us to save our response

        /*
        RestAssured request has similar structure to BDD scenarios:
        Start building the request part of the test


        given() --> used for request setup and authentication
        Syntactic sugar,
        when() --> to specify type of HTTP request: get, put, post, delete , patch, head, etc..
        then() --> to verify response, perform assertions

        //below information on console ; is coming from prettyPeek() (as metadata.) In postman we can see this info in header!
//HTTP/1.1 200 OK
//Date: Sun, 17 May 2020 17:49:37 GMT
//Content-Type: application/json
//ETag: "xfpAm9x57e33U1Dl8yyjlpmQLl+Qna+crKCi0D3uC411jNhiCvtge/43GaRp/QDxcL8maPYmmoosDvJywG+M5A=="
//Transfer-Encoding: chunked
         */
    }

        @Test
        @DisplayName("Get employee under specific ID")
        public void  getOneEmployee() {
            //in URl we can specify path and query parameters
            // path parameters are used to retrieve specific resource: for example 1 employee not all of them
            // {id} --> path variable , that will be replace with a value after coma
            //after when()--> we specify HTTP request type/method/verb
            //The path parameters E.g if path is "/book/{hotelId}/{roomNumber}" you can do <code>get("/book/{hotelName}/{roomNumber},
            //if you want to use response object later,
            //like assigning string into a variable for reusability
            Response response = given().
                    baseUri(BASE_URL).
                    when().get("/employees/{id}", 100).prettyPeek();

            //how to verify response? --> use assertions
            response.then().statusCode(200);// to verify that status is 200
            int statusCode = response.statusCode(); // to save status code in variable

            //if assertions fails, you will get this kinf of message:
            /*
            java.lang.AssertionError: 1 expectation failed
            Expected status code <201> but was <200>
            200 is always expected status code after Get request
             */

            /*
            given base URI
            when user sends get request to ?/countries"
            then user verifies that status cide is 200
             */
        }
            @Test
            @DisplayName("Get list of all countries and verify that statur code is 200")
            public void  getCountries(){
            given().
                    baseUri(BASE_URL).
                    when().get("/countries/").prettyPeek().
                    then().statusCode(200);
                //statsCode() --> to verify status line
                //statusCode(200).statusLine("OK"); --> this will fail, "OK" is not exact line message
                // no need to specify statusLine();
                //every response must have status code



        }


    @Test
    @DisplayName("Get list of all jobs and verify that status code is 200")
    public void  getJobs() {
        given().
                baseUri(BASE_URL).
                when().get("/jobs/").prettyPeek().
                then().statusCode(200);
    }



}
