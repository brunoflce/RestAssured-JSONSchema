package br.com.brunofelice.JSONSchema;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;


public class TestJSONSchemaValidator {

    @BeforeAll
    static void beforeTest() {
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void Validate() {
        when().
               get("/users?page=2").
        then().
               assertThat().
               body(matchesJsonSchemaInClasspath("model.json")).
               statusCode(HttpStatus.SC_OK).
               body("data[4].first_name", equalTo("George")).
               body("data.first_name", hasItems("George", "Rachel"));
    }
}


