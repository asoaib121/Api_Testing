package day8;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUser {

    @Test(priority = 2)
    void test_getUser(ITestContext context){

        int id =(Integer) context.getAttribute("user_id");
       // int id =(Integer) context.getSuite().getAttribute("user_id");

        String bearerToken="5ec6bc60fd383068ab07696a439a2fa6cb802e103e06b9f92ffaa1230cabe739";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParams("id",id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();


    }
}
