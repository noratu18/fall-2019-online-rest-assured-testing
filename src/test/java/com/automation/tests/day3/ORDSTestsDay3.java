package com.automation.tests.day3;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestsDay3 {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.224.118.38:1000/ords/hr";
    }

    /**
     * given resource path parameter is "/regions/{id}"
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void verifyFirstRegion(){

        given()
                .pathParam("id", 1)
         .when()
                .get("/regions/{id}").prettyPeek()
         .then()
                .assertThat().statusCode(200)
                .body("region_name", is("Europe"))
                .body("region_id",is(1))
                .time(lessThan(5L), TimeUnit.SECONDS); //verify that response time is less than 5 seconds
                // 5L--> Long

    }

    @Test
    public void verifyEmployee(){
        Response response = given()
                        //    .contentType(ContentType.JSON)//this for put, post
                            .accept(ContentType.JSON) // this for retrieve data, receive
                            .when()
                            .get("/employees");

        //* JsonPath is an alternative to using XPath for easily getting values from a Object document. It follows the
        // * Groovy <a href="http://docs.groovy-lang.org/latest/html/documentation/#_gpath">GPath</a> syntax when getting
        // an object from the document. You can regard it as an alternative to XPath for JSON.
        //Groovy --> is another programming language that based on Java
        JsonPath jsonPath = response.jsonPath();

        //items --> names of the array where all employees are stored
        // we use items[0]--> because the data is in array
        //GPath syntax, something like XPath but different, GPath use Groovy syntax
        String nameOfFirstEmployee = jsonPath.getString("items[0].first_name");// 0 --> to get first item in th elist
        String nameOfLastEmployee = jsonPath.getString("items[-1].first_name"); //-1 to get last item in the list, like length()-1

        System.out.println("First name Of 1st Employee = " + nameOfFirstEmployee);
        System.out.println("First name Of Last Employee = " + nameOfLastEmployee);

        //Object --> because Object is general data type , we have Strings, int in our data
        //Map<String, Object> firstEmployee = jsonPath.getObject("items[0]", Map.class);
        Map<String, Object> firstEmployee = jsonPath.get("items[0]");
     //   Map<String, ?> firstEmployee = jsonPath.get("items[0]"); --> we can use ? instead of Object cos the data type is different in value
        System.out.println("firstEmployee = " + firstEmployee);



    }



}
