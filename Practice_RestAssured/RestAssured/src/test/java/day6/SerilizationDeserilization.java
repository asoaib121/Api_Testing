package day6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

public class SerilizationDeserilization {

    @Test(priority = 1)
    void convertPojo2Json() throws JsonProcessingException {

        //created java object using pojo class
        Student stupojo=new Student();

        stupojo.setName("Scott");
        stupojo.setLocation("Brance");
        stupojo.setPhone("123456");
        String courseArr[]={"C","C++"};
        stupojo.setCourses(courseArr);

        //convert java object ---> json object (serilization)
        ObjectMapper objMapper=new ObjectMapper();

        String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);

        System.out.println(jsondata);
    }


    //Json -----> Pojo  (De-Serilization)
    @Test (priority = 2)
    void convertJson2Pojo()  throws JsonProcessingException
    {

        String jsondata="{\n" +
                "  \"name\" : \"Scott\",\n" +
                "  \"location\" : \"Brance\",\n" +
                "  \"phone\" : \"123456\",\n" +
                "  \"courses\" : [ \"C\", \"C++\" ]\n" +
                "}";

        ObjectMapper objMapper=new ObjectMapper();
        Student stupojo=objMapper.readValue(jsondata,Student.class);

        System.out.println("Name:"+stupojo.getName());
        System.out.println("Location:"+stupojo.getLocation());
        System.out.println("Name:"+stupojo.getPhone());
        System.out.println("Name:"+stupojo.getCourses()[0]);
        System.out.println("Name:"+stupojo.getCourses()[1]);
    }
}
