package test;

import baseURL.DummyBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;

public class D20 extends DummyBaseURL {

    // http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    //    gonderdigimizde donen response’un status code’unun 200,
    //    content Type’inin application/json ve body’sinin asagidaki gibi oldugunu test edin.
    //
    //	Expected Body
    //    {
    //    "status":"success",
    //    "data": {
    //            "id": 3,
    //            "employee_name":"Ashton Cox",
    //            "employee_salary":86000,
    //            "employee_age":66,
    //            "profile_image":""
    //            },
    //    "message":"Successfully! Record has been fetched."
    //    }


    @Test
    public void get01(){




        //url
        specDummy.pathParams("pp1","employee","pp2",3);

        //exp data
        TestDataDummy testDataDummy=new TestDataDummy();

        JSONObject expData =testDataDummy.expectedBodyOlusturJson();

        //Res kaydet
        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}");


        //assert
        Assert.assertEquals(testDataDummy.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(testDataDummy.contentType,response.getContentType());


        JsonPath resJP=response.jsonPath();//body için
        Assert.assertEquals(expData.get("status"),resJP.get("status"));
        Assert.assertEquals(expData.get("message"),resJP.get("message"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),resJP.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),resJP.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),resJP.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"),resJP.get("data.profile_image"));





    }
}
