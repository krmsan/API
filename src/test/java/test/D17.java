package test;

import baseURL.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D17 extends HerokuappBaseUrl {


    @Test
    public void get01() {

         /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
        request gonderdigimizde donen response’un status code’unun 200 oldugunu
        ve Response’ta 3163 id'ye sahip bir booking oldugunu test edin
     */

        //url yaz
        specHerokuapp.pathParam("pp1", "booking");


        //respons
        Response response = given().spec(specHerokuapp).when().get("/{pp1}");

        // response.prettyPrint();

        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasItem(3163));


    }


    @Test
    public void get02() {


        /*
        2- https://restful-booker.herokuapp.com/booking endpointine gerekli
        Query parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon
        oldugunu test edecek bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
    */

        // 1 - URL hazirla

        specHerokuapp.pathParam("pp1", "booking").queryParam("firstname", "Eric");//

        Response response = given().spec(specHerokuapp).when().get("/{pp1}");//queryParamı yazmaya gerek yokmus
        response.prettyPrint();


        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasSize(4));

    }


    @Test
    public void get03() {



        /*
        3- https://restful-booker.herokuapp.com/booking endpointine gerekli Query
         parametrelerini yazarak “firstname” degeri “Jim” ve “lastname” degeri
         “Jackson” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
         donen response’un status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip
         en az bir booking oldugunu test edin.
    */

        specHerokuapp.pathParam("pp1", "booking").queryParams("firstname", "Jim", "lastname", "Jackson");


        Response response = given().spec(specHerokuapp).when().get("/{pp1}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasSize(0));
    }

}
