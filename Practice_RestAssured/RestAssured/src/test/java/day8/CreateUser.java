package day8;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateUser {

    @Test(priority = 1)
    void test_createUser(ITestContext context){
        Faker faker=new Faker();
        JSONObject data=new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String bearerToken="5ec6bc60fd383068ab07696a439a2fa6cb802e103e06b9f92ffaa1230cabe739";
int id= given()
                .headers("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())
        .when()
                .post("https://gorest.co.in/public/v2/users")
               .jsonPath().getInt("id");
        System.out.println("Generated id"+id);
        context.setAttribute("user_id",id);

        //when ful package run different different test case then use this code.
        //context.getSuite().setAttribute("user_id",id);
    }

}
