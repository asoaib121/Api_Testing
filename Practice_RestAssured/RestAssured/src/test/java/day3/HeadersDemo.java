package day3;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HeadersDemo {

    @Test(priority = 1)
    void testHeaders() {
        given()

        .when()
                .get("https://www.google.com")

// error achee header a
        .then()
                .header("Cache-Control","private, max-age=0")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Transfer-Encoding","chunked")
                .log().all();
    }

    @Test(priority = 2)
    void getHeaders() {

        Response res = given()
                        .when()
                           .get("https://www.google.com/");


        //String heeadervalue=res.getHeader("Connection");
        //System.out.println("The value of Content-type header is:"+heeadervalue);

        //get all headers info
        Headers myheaders= res.getHeaders();

        for(Header k:myheaders){
            System.out.println(k.getName()+"                            "+k.getValue());
        }

    }

}