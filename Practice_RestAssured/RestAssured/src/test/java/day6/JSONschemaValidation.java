package day6;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JSONschemaValidation {

    @Test
    void jsonschemavalidation(){
        given()
                .when()
                .get("http://localhost:3000/book")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("storeJsonSchema.json"));
    }
}
