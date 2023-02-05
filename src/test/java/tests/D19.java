package tests;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testsData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class D19 extends JsonPlaceHolderBaseUrl {

    // https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
    //    request yolladigimizda donen response’in
    //    status kodunun 200, content type’inin “application/json; charset=utf-8”,
    //    Connection header degerinin “keep-alive”
    //    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
    //
    //    Request Body
    //
    //        {
    //        "title":"Ali",
    //        "body":"Merhaba",
    //        "userId":10,
    //        "id":70
    //        }
    //
    //    Expected Data
    //
    //        {
    //        "title":"Ali",
    //        "body":"Merhaba",
    //        "userId":10,
    //        "id":70
    //        }

    @Test
    public void put01() {


        //url ve body
        specJsonPlace.pathParams("pp1", "posts", "pp2", 70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        JSONObject reqBody = testDataJsonPlaceHolder.requestBodyOlusturJSON();

        //expected data
        JSONObject expData=testDataJsonPlaceHolder.requestBodyOlusturJSON();

        //response kaydet
        Response response=given().
                spec(specJsonPlace).
                contentType(ContentType.JSON).//bu satır olmazsa sadece {"id": 70} döner
                when().
                body(reqBody.toString()).
                put("/{pp1}/{pp2}");

        response.prettyPrint();//   { "id": 70,"title": "Ali","body": "Merhaba","userId": 10} yazarsan boyle doner





        //assert
        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(testDataJsonPlaceHolder.contentType,response.getContentType());
        Assert.assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri,response.getHeader("Connection"));

        //body için ise
        JsonPath resJP=response.jsonPath();
       /*
        int x=3;
        String rakam=x.String();        bu olmaz boyle yukarıda ne yapılıyor
        */

        Assert.assertEquals(expData.get("id"),resJP.get("id"));
        Assert.assertEquals(expData.get("userId"),resJP.get("userId"));
        Assert.assertEquals(expData.get("title"),resJP.get("title"));
        Assert.assertEquals(expData.get("body"),resJP.get("body"));
    }
}
