package day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponse {
    @Test
    void testXMLResponse(){
        //Approach1: Fluent API validation
        given() //Using RestAssured's fluent API
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .statusCode(200)
                .header("Content-Type","application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[1].name",equalTo("Vijay Bharath Reddy"));

        //Approach2
        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");

        String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNo,"1");

        String travelName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[1].name").toString();
        Assert.assertEquals(travelName,"Vijay Bharath Reddy");
    }

    @Test
    void testXMLResponseBody(){
        //Approach2
        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlobj = new XmlPath(res.asString());
        //Verify total number of travellers
        List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travellers.size(),10);

        //verify traveller name is present in response
        List<String> traveller_names = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        boolean status = false;
        for(String travellername: traveller_names) {
            if(travellername.equals("Vijay Bharath Reddy")){
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);
    }
}