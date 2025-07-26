package day8;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Delete_user {

    @Test (priority = 4)
    void test_DeleteUser(ITestContext context){

        int id=(Integer) context.getAttribute("user_id");;
        String bearerToken="5ec6bc60fd383068ab07696a439a2fa6cb802e103e06b9f92ffaa1230cabe739";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParams("id",id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();

    }

}
