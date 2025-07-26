package day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateUser {

    @Test(priority = 3)
    void test_updateUsers(ITestContext context){

        Faker faker=new Faker();

        JSONObject data=new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        int id=(Integer) context.getAttribute("user_id");;
        String bearerToken="5ec6bc60fd383068ab07696a439a2fa6cb802e103e06b9f92ffaa1230cabe739";
                given()
                      .headers("Authorization","Bearer "+bearerToken)
                      .contentType("application/json")
                      .body(data.toString())
                        .pathParams("id",id)
                .when()
                      .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                        .log().all();

        System.out.println("Generated id"+id);
    }
}
