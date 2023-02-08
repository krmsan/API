package test;

import baseURL.DummyBaseURL;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyData;
import pojos.PojoDummyExpectedBody;

import static io.restassured.RestAssured.given;

public class D29 extends DummyBaseURL {




    //http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    //     gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
    //
    //	Response Body
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
    public void get01(){//reqBody eihtiyac yok get cunku

        specDummy.pathParams("pp1","employee","pp2",3);


        //expDAta hazırla
        //
        //                  ========= NOT:   ==========
        // data tipi olarak ya JSONObject olarak yada MAP olarak hazirla
        //yontem olarak da ya TestDAtaclaslarında methodlar yap ya clasta olustur yada pojo olarak yap

        //response         ========= NOT:   ==========
        // statuscode, content-type gibi direkt respons uzerinden sorgulanabilir
        // response body sorgusu iseise  then().assertThat() den sonra Matchers clasından,
        // yada  JUnit Assert yada softAssert olarak yapılır.

        //JSONObject te getJSONObject(data).get(id) lar ile <(yani ayrı ayrı methodlarda cgrıyoruz--(data) (id))
        // ----JsonPath te ise get(data.id) ile seklinde yapılır (tek methodda . ile ayırıyoruz)
        //map de ise serialization ve deserialization aracılıgıyla dataları  MAp e Cast ediyoruz

        //veya pojo ile   yap


        PojoDummyData data=new PojoDummyData(3,"Ashton Cox",86000,66,"profile_image");


        PojoDummyExpectedBody expBody=new PojoDummyExpectedBody("success",data,"Successfully! Record has been fetched.");


        //res kaydet
        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}");

        //assert yap


       PojoDummyExpectedBody resPojo= response.as(PojoDummyExpectedBody.class);

        Assert.assertEquals(expBody.getStatus(),resPojo.getStatus());
        Assert.assertEquals(expBody.getMessage(),resPojo.getMessage());

        Assert.assertEquals(expBody.getData().getEmployee_name(),resPojo.getData().getEmployee_name());






    }
}
