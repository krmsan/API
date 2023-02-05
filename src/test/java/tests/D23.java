package tests;

import baseURL.DummyBaseURL;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testsData.TestDataDummy;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class D23 extends DummyBaseURL {


    // http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET
    //    request gonderdigimizde donen response’un status code’unun 200,
    //    content Type’inin application/json ve body’sinin asagidaki gibi
    //    oldugunu test edin.
    //
    //    Response Body
    //    {
    //    "status":"success",
    //    "data":{
    //            "id":3,
    //            "employee_name":"Ashton Cox",
    //            "employee_salary":86000,
    //            "employee_age":66,
    //            "profile_image":""
    //            },
    //    "message":"Successfully! Record has been fetched."

    //    }


    @Test
    public void get01() {


        //url
        specDummy.pathParams("pp1", "employee", "pp2", 3);

        //expected
        TestDataDummy testDataDummy = new TestDataDummy();
        HashMap<String, Object> expData = testDataDummy.expectedBodyOlusturMap();


        //kaydet
        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        //assert
        Assert.assertEquals(testDataDummy.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(testDataDummy.contentType, response.getContentType());

        //responsu hasmap donustur
        HashMap<String, Object> resBodyMap = response.as(HashMap.class);

        Assert.assertEquals(expData.get("status"),resBodyMap.get("status"));
        Assert.assertEquals(expData.get("message"),resBodyMap.get("message"));


        //inner data için casting yap
        Assert.assertEquals(((HashMap<String,Object>)expData.get("data")).get("employee_name"),
                ((HashMap<String,Object>)resBodyMap.get("data")).get("employee_name"));

        Assert.assertEquals(((HashMap)expData.get("data")).get("employee_salary"),
                ((HashMap)resBodyMap.get("data")).get("employee_salary"));


    }
}
