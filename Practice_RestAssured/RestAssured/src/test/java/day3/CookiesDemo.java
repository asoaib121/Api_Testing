package day3;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CookiesDemo {

    @Test(priority = 1)
    void testCookies(){
        given()

        .when()
                .get("https://www.google.com/")


        .then()
                .log().all();
    }
 @Test(priority = 2)
    void getCookiesInfo(){
        Response res=given()
        .when()
                .get("https://www.google.com");


        //get single cookie info
//        String cookie_value=res.getCookie("AEC");
//        System.out.println("value of cookies is===>"+cookie_value);

        //get all cookies info
        Map<String,String> cookies_value= res.getCookies();
        // System.out.println(cookies_value.keySet());

     for(String k:cookies_value.keySet()){
         String cookie_values=res.getCookie(k);
         System.out.println(k+ "       "+cookie_values);
     }

    }
}
