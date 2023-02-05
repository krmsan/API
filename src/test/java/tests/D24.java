package tests;

import baseURL.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testsData.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class D24 extends HerokuappBaseUrl {


    //https://restful-booker.herokuapp.com/booking url'ine asagidaki
    //      body'ye sahip bir POST request gonderdigimizde donen response'un
    //      id haric asagidaki gibi oldugunu test edin.
    //
    //        Request body
    //   {
    //        "firstname" : "Ali",
    //        "lastname" : "Bak",
    //        "totalprice" : 500,
    //        "depositpaid" : false,
    //        "bookingdates" : {
    //                 "checkin" : "2021-06-01",
    //                 "checkout" : "2021-06-10"
    //                          },
    //        "additionalneeds" : "wi-fi"
    //    }
    //
    //       Response Body
    //       {
    //       "bookingid":24,
    //       "booking":{
    //           "firstname":"Ali",
    //           "lastname":"Bak",
    //           "totalprice":500,
    //           "depositpaid":false,
    //           "bookingdates":{
    //               "checkin":"2021-06-01",
    //               "checkout":"2021-06-10"
    //           },
    //           "additionalneeds":"wi-fi"
    //           }
    //       }


    @Test
    public void post01() {

        //url
        specHerokuapp.pathParam("pp1", "booking");


        TestDataHerokuapp testDataHerokuapp = new TestDataHerokuapp();

        HashMap<String, Object> reqBody = testDataHerokuapp.bookingOlusturMap();

        //exp
        HashMap<String, Object> expData = testDataHerokuapp.expBodyOlusturMap();//bookingOlusturMap() yazarsan calısmaz tabi

        //respons kaydet




        Response response = given().
                spec(specHerokuapp).
                contentType(ContentType.JSON).
                when().
                body(reqBody).
                post("/{pp1}");

        //assert


        HashMap <String,Object> respMap = response.as(HashMap.class);




        Assert.assertEquals(  ((HashMap)(expData.get("booking"))).get("firstname")  ,
                ((Map)(respMap.get("booking"))).get("firstname") );//hashmap yazınca calısmıyor



        Assert.assertEquals((((HashMap) expData.get("booking"))).get("lastname"),
                ((Map) respMap.get("booking")).get("lastname"));//hashmap yazınca calısmıyor

        Assert.assertEquals(((HashMap) expData.get("booking")).get("totalprice"),
                ((Map) respMap.get("booking")).get("totalprice"));

        Assert.assertEquals(((Map) expData.get("booking")).get("depositpaid"),
                ((Map) respMap.get("booking")).get("depositpaid"));

        Assert.assertEquals(((Map) expData.get("booking")).get("additionalneeds"),
                ((Map) respMap.get("booking")).get("additionalneeds"));


        Assert.assertEquals(((Map) (((Map) expData.get("booking")).get("bookingdates"))).get("checkin"),
                ((Map) ((Map) respMap.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map) (((Map) expData.get("booking")).get("bookingdates"))).get("checkout"),
                ((Map) ((Map) respMap.get("booking")).get("bookingdates")).get("checkout"));


        /*
eger expData yerine ReqBody yazarsan calısmaz tabi
        Assert.assertEquals(((Map) (((Map) reqBody.get("booking")).get("bookingdates"))).get("checkout"),
                ((Map) ((Map) respMap.get("booking")).get("bookingdates")).get("checkout"));


         */
    }
}
