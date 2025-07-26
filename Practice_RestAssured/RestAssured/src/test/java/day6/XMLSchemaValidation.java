package day6;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLSchemaValidation {

    void xmlSchemavalidation(){
        given()
                .when()
                .get("http://restAPI.adequateshop.com/api/Traveler") //this url do not work.
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd")); //traveler.xsd file do not stay.because xml file not found.
    }
}
