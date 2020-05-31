package com.automation.tests.day9;

import com.automation.utilities.ConfigurationReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class SchemaValidation {

    // given(), when() --> request
    // then() --> response
    // Everything after then "then()"
    // is pretty much an assertion to see if something
    // is true. It checks to see if the response for
    // the GET request (id 313) matches the data in "schema".

        @BeforeAll
        public static void beforeAll(){
            baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
            authentication = basic("admin", "admin");

    }

    @Test
    public void schemaValidationTest(){

            //get json schema and store ir in a file object
        File schema = new File("spartan_schema.json");

        get("/spartans/{id}", 313)
                .prettyPeek()

                .then()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}


