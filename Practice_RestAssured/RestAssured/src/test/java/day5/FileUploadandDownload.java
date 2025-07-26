package day5;
import org.testng.annotations.Test;

import java.io.File;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUploadandDownload {

    @Test
    void singelFileUpload() {
        File myfile=new File("\"D:\\upa\\Test1.txt\"");
        given()
                .multiPart("file",myfile)
                .contentType("multipart/from-data")


        .when()
                        .post("https://localhost:8080/uploads")
        .then()
                        .statusCode(200)
                        .body("fileName",equalTo("Test.txt"))
        .log().all();

    }
    @Test(priority=1)
    void MultipleFileUpload() {
        File myfile1=new File("\"D:\\upa\\Test1.txt\"");
        File myfile2=new File("\"D:\\upa\\Test2.txt\"");

        File filearr[]={myfile1,myfile2};
        given()
                .multiPart("file",filearr)
                .contentType("multipart/from-data")


                .when()
                .post("https://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("Test.txt"))
                .body("[1]fileName",equalTo("Test.txt"))
                .log().all();

    }
    @Test(priority = 2)
    void fileDownload(){

        given()

                .when()
                .get("http://localhost8080/downloadFile/test1.txt")
                .then()
                .statusCode(200)
                .log().all();

    }
}
