package day1;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * given()
 *    content type, set cookies, add auth, add params, set headers info etc....
 *  when()
 *    get, post, put, delete
 * then()
 *    validate status code, extract response, extract headers cookies & response body...
 */

public class HTTPRequests {
    int id;

    @BeforeMethod
    public static void setup() {
//         given()


    }



    @Test(priority=1)
    void getUser()
    {
        given()
                .header("x-api-key", "reqres-free-v1")


        .when()
                .get("https://reqres.in/api/users?page=2")

       .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }
    @Test(priority = 2)
    void createUser(){

        HashMap data = new HashMap();
        data.put("name","morpheus");
        data.put("job","leader");

       given()
               .header("x-api-key", "reqres-free-v1")


        .when()
                .post("https://reqres.in/api/users");

//        .then()
//                .statusCode(201)
//                .log().all();
    }
    @Test(priority = 3,dependsOnMethods = ("createUser"))
    void updateUser(){

        HashMap<String, String> data = new HashMap<>();
        data.put("name","john");
        data.put("job","teacher");

        given()
                .header("x-api-key", "reqres-free-v1")

        .when()
                .put("https://reqres.in/api/users/2")

        .then()
                .statusCode(200)
                .log().all();

    }
    @Test(priority=4)
    void deleteUser(){
        given()
                .header("x-api-key", "reqres-free-v1")

       . when()
                .delete("https://reqres.in/api/users/2")

       .then()
                .statusCode(204)
                .log().all();


    }
}
