package day3;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PathAndQueryParameters {

    @Test
    void testQueryAndPathParameters(){
        given()
                .header("x-api-key", "reqres-free-v1")
                .pathParams("myPath","users")
                .queryParam("page",2)
                .queryParam("id",7)

        .when()
                .get("https://reqres.in/api/{myPath}")


        .then()
                .statusCode(200)
                .log().all();

    }
}
