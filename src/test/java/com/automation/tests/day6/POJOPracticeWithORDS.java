package com.automation.tests.day6;

import com.automation.pojos.Employee;
import com.automation.pojos.Link;
import org.junit.jupiter.api.BeforeAll;
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


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POJOPracticeWithORDS {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("ORDS.URI");

    }

    @Test
    public void getEmployeeTest(){
        // to send request
    Response response = get("/employees/{id}", 100).prettyPeek();

    //saving this employee as an employee POJO
        Employee employee = response.as(Employee.class);

        System.out.println(employee);

        List<Link> link = employee.getLinks();
        System.out.println("links = " + link);


    }
}
