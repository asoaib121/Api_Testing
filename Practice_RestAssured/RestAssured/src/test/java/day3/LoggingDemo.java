package day3;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class LoggingDemo {
   @Test
    void testLogs(){
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
               // .log().body();
               // .log().cookies();
                .log().all();
    }


}
