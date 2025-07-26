package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

//For data.json database
//with student field for SDET QA channel
public class ParsingJsonResponse2 {


    //some error


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

        String students=res.jsonPath().getString("students[0].name");
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
        JSONObject jo= new JSONObject(res.toString());  //converting response to Json object Type
        boolean status=false;
        for(int i=0;i<jo.getJSONArray("students").length();i++){
            String studentsName=jo.getJSONArray("students").getJSONObject(i).getString("name");
            if(studentsName.equals("The Lord of the Rings")) {
                status = true;
                break;
            }
        }

        Assert.assertEquals(status,false);

        //validation total price of books
        double totalprice=0;
        for(int i=0;i< jo.length();i++){
            String cost=jo.getJSONArray("department").getJSONObject(i).get("cost").toString();
            totalprice=totalprice+Double.parseDouble(cost);
        }
        System.out.println("total price of books: "+totalprice);
        Assert.assertEquals(totalprice,53.92);
    }
}
