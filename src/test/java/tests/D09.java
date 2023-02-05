package tests;

import io.restassured.http.ContentType;

import io.restassured.response.Response;

import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class D09 {


    // https://restful-booker.herokuapp.com/booking
    //             url’ine asagidaki body'ye sahip
    //            bir POST request gonderdigimizde
    //                       {
    //                            "firstname" : "Ali",
    //                            "lastname" : "Bak",
    //                            "totalprice" : 500,
    //                            "depositpaid" : false,
    //                            "bookingdates" : {
    //                                "checkin" : "2021-06-01",
    //                                "checkout" : "2021-06-10"
    //                            },
    //                            "additionalneeds" : "wi-fi"
    //                        }
    //            donen Response’un,
    //            status code’unun 200,
    //            ve content type’inin application-json,
    //            ve response body’sindeki
    //                "firstname“in,"Ali",
    //                ve "lastname“in, "Bak",
    //                ve "totalprice“in,500,
    //                ve "depositpaid“in,false,
    //                ve "checkin" tarihinin 2021-06-01
    //                ve "checkout" tarihinin 2021-06-10
    //                ve "additionalneeds“in,"wi-fi"
    //            oldugunu test edin


    @Test
    public void post01() {

        String url = "https://restful-booker.herokuapp.com/booking";
        //body olusturuyoruz
        JSONObject innerBody = new JSONObject();
        innerBody.put("checkin", "2021-06-01");
        innerBody.put("checkout", "2021-06-10");

        JSONObject reqbody = new JSONObject();//NOT: alttakilerden birini yorum satırı yapsan 500 hatası veriyor
        reqbody.put("firstname", "Ali");
        reqbody.put("lastname", "Bak");
        reqbody.put("totalprice" , 500);
        reqbody.put("depositpaid", false);
        reqbody.put("additionalneeds", "wi-fi");
        reqbody.put("bookingdates", innerBody);




        //kaydediyoruz
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqbody.toString()).
                post(url);


        response.prettyPrint();




        //assert yapiyoruz
        response.then().

                assertThat().

                statusCode(200).

                contentType(ContentType.JSON).
                body("booking.firstname", equalTo("Ali"),
                        "booking.lastname", equalTo("Bak"),
                        "booking.bookingdates.checkin", equalTo("2021-06-01"));

    }
}
