package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

//For db.json Database
//without students field code
public class ParsingJSONResponseData {

    @Test (priority = 1)
    void testJsonResponse() {

//        given()
//                .contentType("ContentType.JSON")
//
//        .when()
//                .get("http://localhost:3000/")
//
//
//        .then()
//                .statusCode(200)
//                .header("Content-Type","application/json")
//                .body("student[0]",equalTo("Fatima Ahmed"));

        Response res=
        given()
                .contentType(ContentType.JSON)

       .when()
                .get("http://localhost:3000/students");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Connection"),"keep-alive");

        String students=res.jsonPath().getString("[0].name");
                Assert.assertEquals(students,"Fatima Ahmed");

    }
    @Test(priority=2)
    void testJsonResponseBodyData() {

        Response res= given()
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/students");
       /* Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Accept-Encoding"),"gzip");

        String StudentName=res.jsonPath().get("students[0].name").toString();
                Assert.assertEquals(StudentName,"Fatima Ahmed"); */

        //JSONObject class
        JSONArray arr= new JSONArray(res.getBody().asString());  //converting response to Json object Type

        //search for title of the book in array -validation 1
        boolean status=false;
        for(int i=0;i< arr.length();i++){
           String studentsName=arr.getJSONObject(i).getString("name");
            if(studentsName.equals("The Lord of the Rings")){
                status=true;
                break;

            }
        }
        Assert.assertEquals(status,false);

        //validation total price of books
        for(int i=0;i< arr.length();i++){
            String studentsName=arr.getJSONObject(i).getString("name");

            }
    }
}
