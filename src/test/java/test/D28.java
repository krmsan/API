package test;

import baseURL.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyExpectedBody;
import pojos.PojoHerokuappBooking;
import pojos.PojoHerokuappBookingDates;
import pojos.PojoHerokuappExpectedBody;

import static io.restassured.RestAssured.given;

public class D28 extends HerokuappBaseUrl {

    //https://restful-booker.herokuapp.com/booking url’ine
    //    asagidaki body'ye sahip bir POST request gonderdigimizde
    //    donen response’un id disinda asagidaki gibi oldugunu test edin.
    //    	                Request body
    //    	           {
    //    	                "firstname" : "Ahmet",
    //    	                "lastname" : “Bulut",
    //    	                "totalprice" : 500,
    //    	                "depositpaid" : false,
    //    	                "bookingdates" : {
    //    	                         "checkin" : "2021-06-01",
    //    	                         "checkout" : "2021-06-10"
    //    	                                  },
    //    	                "additionalneeds" : "wi-fi"
    //    	            }
    //
    //
    //    	            	Response Body = Expected Data
    //    	            	{
    //                    "bookingid":24,
    //                    "booking":{
    //                        "firstname":"Ahmet",
    //                        "lastname":"Bulut",
    //                        "totalprice":500,
    //                        "depositpaid":false,
    //                        "bookingdates":{
    //                            "checkin":"2021-06-01",
    //                            "checkout":"2021-06-10"
    //                                        }
    //                        ,
    //                        "additionalneeds":"wi-fi"
    //                              }
    //                    }

    @Test
    public void post01() {


        //url
        specHerokuapp.pathParam("pp1", "booking");

        //body
        PojoHerokuappBookingDates bookingDates = new PojoHerokuappBookingDates("2021-06-01", "2021-06-10");
        PojoHerokuappBooking reqBody = new PojoHerokuappBooking("Ahmet", "Bulut", 500, false, "wi-fi", bookingDates);


        //expDAta hazırla
        PojoHerokuappExpectedBody expData = new PojoHerokuappExpectedBody(24, reqBody);

        //response kaydet
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON).when()
                .body(reqBody).
                post("/{pp1}");

        //assert

        PojoHerokuappExpectedBody respPojo = response.as(PojoHerokuappExpectedBody.class);

        Assert.assertEquals(expData.getBooking().getFirstname(), respPojo.getBooking().getFirstname());
        Assert.assertEquals(expData.getBooking().getLastname(), respPojo.getBooking().getLastname());
        Assert.assertEquals(expData.getBooking().getBookingdates().getCheckin(), respPojo.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(expData.getBooking().isDepositpaid(), respPojo.getBooking().isDepositpaid());

        // eger Boolean olarak (wrapper) yazsaydık getdepositpaid() yaparagetirir
        // .boolean-> boyle iken isdepositpaid() olrak getirir


    }
}
