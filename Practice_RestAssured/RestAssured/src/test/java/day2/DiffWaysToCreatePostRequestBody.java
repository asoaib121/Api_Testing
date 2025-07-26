package day2;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Different ways to create POST request body
 * 1)Post request body using Hashmap
 * 2)Post request body creation using Org.json
 * 3)Post request body creation using POJO class
 * 4)Post request body using external json file data
 */

public class DiffWaysToCreatePostRequestBody {
    @Test(priority=1)
    void testPostUsingHashMap(){

        /* when you don't want HashMap and JSONObject, then you use Pojo_PostRequest
        //Pojo_PostRequest data=new Pojo_PostRequest();
        data.setid("id");
        data.setname("Rahim khan");
        data.setroll("101"); */

        /**
         * jsonobject or external json or HashMap or Pojo_PostRequest any one
         * program you use,If you want.
         * External JsonFile
         * File f=new File(".\body.json");
         * FileReader fr=new FileReader(f);
         * JSONTokener jt=new JSONTokener(fr);
         * JSONObject data=new JSONObject(jt);
         */

        //HashMap and JSONObject any library use possible
      HashMap data=new HashMap();
        data.put("id","ID");
        data.put("name","Rahim Khan");
        data.put("roll","101");

        //You want Multiple Subject post, So String array
        String program[]={"CSE","EEE","ME"};
        data.put("department",program);
        data.put("semester","5th");
        data.put("email","rahim@example.com");
        data.put("phone","01711223344");

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .body("id",equalTo("ID"))
                .body("name",equalTo("Rahim Khan"))
                .body("roll",equalTo("101"))
                .body("department[0]",equalTo("CSE"))
                .body("department[1]",equalTo("EEE"))
                .body("department[2]",equalTo("ME"))
                .body("semester",equalTo("5th"))
                .body("phone",equalTo("01711223344"))
                .body("email",equalTo("rahim@example.com"))
                .header("Content-Type","application/json")
                .log().all();
    }
    @Test(priority = 2)
    void testDelete()
    {
      given()

      .when()
              .delete("http://localhost:3000/students/ID")

      .then()
              .statusCode(200);
   }
}
