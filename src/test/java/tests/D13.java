package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class D13 {


    //http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    //    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
    //
    //        Response Body
    //        {
    //        "status":"success",
    //        "data":{
    //                "id":3,
    //                "employee_name":"Ashton Cox",
    //                "employee_salary":86000,
    //                "employee_age":66,
    //                "profile_image":""
    //                },
    //        "message":"Successfully! Record has been fetched."
    //        }


    @Test
    public void get01() {

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        JSONObject innerBody = new JSONObject();
        innerBody.put("id", 3);
        innerBody.put("employee_name", "Ashton Cox");
        innerBody.put("employee_salary", 86000);
        innerBody.put("employee_age", 66);
        innerBody.put("profile_image", "");

        JSONObject expbody = new JSONObject();
        expbody.put("status", "success");
        expbody.put("data", innerBody);
        expbody.put("message", "Successfully! Record has been fetched.");


        Response response = given().when().get(url);

        JsonPath jspathrespons=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(jspathrespons.get("status"),expbody.get("status"));
        softAssert.assertEquals(jspathrespons.get("message"),expbody.get("message"));
        softAssert.assertEquals(jspathrespons.get("data.employee_name"),expbody.getJSONObject("data").get("employee_name"));
        //boyle olmaz softAssert.assertEquals(jspathrespons.get("data.employee_name"),expbody.get("data.employee_name"));
        //JSONObject expbody olanı  expbody.getJSONObject("data").get() seklinde yaziyoruz.


        //

        softAssert.assertAll();

    }
}
