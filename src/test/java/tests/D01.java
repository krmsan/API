package tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D01 {


    //C1_Get_ApiSorgulama
    //https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
    //status code’unun 200,
    //ve content type’inin application/json; charset=utf-8,
    //ve Server isimli Header’in degerinin Cowboy,
    //ve status Line’in HTTP/1.1 200 OK
    //ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.




    @Test
    public void get01(){

        // 1 - Gonderecegimiz Request icin gerekli olan URL ve ihtiyacimiz varsa Body hazirla

        String url = "https://restful-booker.herokuapp.com/booking/707";

        // 2 - Eger soruda bize verilmisse Expected Data hazirla

        // 3 - Bize donen Response'i Actual Data olarak kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        System.out.println("dddddddddddddd");
        response.print();

        System.out.println("Server Header'inin tum Degeri : " +response.getHeaders());
        System.out.println("Status Code : " +response.getStatusCode());
        System.out.println("Content Type : " +response.getContentType());
        System.out.println("Server Header'inin Degeri : " +response.getHeader("Server"));
        System.out.println("Status Line : " +response.getStatusLine());
        System.out.println("Response Suresi : " +response.getTime());

        // 4 - Expected Data ile Actual datanin karsilastirmasi - Assertion




    }
}
