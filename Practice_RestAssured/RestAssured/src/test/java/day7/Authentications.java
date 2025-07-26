package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

    @Test(priority=1)
    void testBasicAuthentication(){

        given()
                .auth().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                 .log().all();
    }

    @Test(priority=2)
    void testDigestAuthentication(){

        given()
                .auth().digest("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority=3)
    void testPreemptiveAuthentication(){

        given()
                .auth().preemptive().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority =4)
    void testBearerTokenAuthentication(){
        String bearerToken="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";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    //this method oauth 1 Authentication
//    @Test(priority = 5)
    void testOAuth1Authentication(){

        given()
                .auth().oauth("consumerkey","consumerSecrat","accessToken","tokenSecrate")
                .when()
                .get("url")
                .then()
                .statusCode(200)
                .log().all();
    }

    //This method oauth2 Authentication
    @Test(priority = 5)
    void testOAuth2Authentication(){
        given()
                .auth().oauth2("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")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }
   @Test
    void testAPIKeyAuthentication(){

//        given()
//                .queryParam("appid","fe9c5ddb7e01d747b4611c3fc9eaf2c")
//                .when()
//                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
//                .then()
//                .statusCode(200)
//                .log().all();

       given()
               .queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")
               .pathParams("mypath","data/2.5/forecast/daily")
               .queryParam("q","Delhi")
               .queryParam("units","metric")
               .queryParam("cnt","7")
               .when()
               .get("https://api.openweathermap.org/{mypath}")
               .then()
               .statusCode(200)
               .log().all();
    }
}
