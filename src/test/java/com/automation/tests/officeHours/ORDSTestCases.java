package com.automation.tests.officeHours;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class ORDSTestCases {

    /*
    Get all the records from the employees table using the {/employees
    verify that number of employees is more than 100

    Get all the employees and their depart id's
    Verify that department id points to the existing record in the
    departments table
    -- verify response is 200
    --verify department name is not empty

     */

    @Test
    public void departmentTest(){
        List<Integer> depIds = given()
                .queryParam("limit", 110)
                .when()
                .get("/employees").jsonPath().getList("items.department_id");
        System.out.println(depIds);

        //remove duplicates
        // Set<Integer>uniqueDepIds = new HashSet<>(depIds); we can use like this one line too
        Set<Integer>uniqueDepIds = new HashSet<>();
        uniqueDepIds.addAll(depIds);
        System.out.println(uniqueDepIds);

        //get each id separately
        for(Integer each : uniqueDepIds){
            // call the department/:id to get the specific department
            // verify 200, verify name is not null
            given()
                    .pathParam("id", depIds)
            .when()
                    .get("/departments/{id}").prettyPeek()
            .then()
                    .statusCode(200)
                    .and().body("department_name", is(not(emptyOrNullString())));

        }


    }

    // static means--> you will have limitations you can't use all the
    // the variables

    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "http://54.146.89.247:1000/ords/hr/";
        /*
         Get all the records from the employees table using the /employees
    verify that number of employees is more than 100

    www.jsonpath.com --> to  see the bird vie of the result,json online path evaluator
         */
    }

    @Test
    public void employeeTest(){

        // turns out you can get only 25 employees per response
        // so we need to use extra query parameter to get all at once
        // row_count

        Response response = given()
                                .queryParam("limit", 110)
                            .when()
                            .get("/employees");

                            response
                                    .then().statusCode(200);

            // get all the employee into a List of maps,
           // each map represents one employee
            List<Map<String, Object>> employees = response.jsonPath()
                    .getList("items");
            System.out.println(employees.size());
            System.out.println(employees.get(1));
            assertThat(employees.size(),greaterThan(100));

       /*
       {
       id:12
       name: nnn
       },
       {
        id:13
       name: nnn
       }
       {
        id:14
       name: nnn
       }

        */
    }






}
