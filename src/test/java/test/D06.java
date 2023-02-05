package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;//hepsinden gecerli olur
//import static org.hamcrest.Matchers.containsString;//yıldız koyunca gerek kalmadı
//import static org.hamcrest.Matchers.lessThan;
//import static org.hamcrest.CoreMatchers.equalTo; bundan da etme
//import static org.hamcrest.core.IsEqual.equalTo; bundan import etme

public class D06 {


    // https://jsonplaceholder.typicode.com/posts
    //         url’ine asagidaki body ile bir POST request gonderdigimizde
    //
    //        {
    //        "title":"API",
    //        "body":"API ogrenmek ne guzel",
    //        "userId":10,
    //        }
    //
    //        donen Response’un,
    //
    //        status code’unun 201,
    //        ve content type’inin application/json
    //        ve Response Body'sindeki,
    //        "title"'in "API" oldugunu
    //        "userId" degerinin 100'den kucuk oldugunu
    //        "body" nin "API" kelimesi icerdigini
    //        test edin.

    @Test
    public void post01() {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "API");
        jsonObject.put("body", "ogrenmek ne guzel");
        jsonObject.put("userId", 10);

        Response response = given().when().contentType(ContentType.JSON).body(jsonObject.toString()).post("https://jsonplaceholder.typicode.com/posts");
        //response.prettyPrint();


        //Assert yapıyoruz
        response.then().assertThat().statusCode(201).contentType("application/json").
                body("title", Matchers.equalTo("API"),
                        "body", Matchers.containsString("guzel"),
                        "userId", Matchers.lessThan(100));


        //body ler ayrı ayrı da olabilir , ile de ayırıp tek body içine de yazabilirsin
        //...body("title", Matchers.equalTo("API").
        //body("body", Matchers.equalTo("API"); //gibi

        // veya Matchers static ı import edip * koyalım matchers clasına
        //   import static org.hamcrest.Matchers.*;

        response.then().assertThat().statusCode(201).contentType("application/json").
                body("title", equalTo("API"),
                        "body", containsString("guzel"),
                        "userId", lessThan(100));
    }
}
