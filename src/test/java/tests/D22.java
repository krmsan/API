package tests;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testsData.TestDataJsonPlaceHolder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class D22 extends JsonPlaceHolderBaseUrl {


    // https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    //    body’e sahip bir PUT request yolladigimizda donen response’in
    //    response body’sinin asagida verilen ile ayni oldugunu test ediniz
    //
    //    Request Body
    //
    //        {
    //        "title":"Ahmet",
    //        "body":"Merhaba",
    //        "userId":10,
    //        "id":70
    //        }
    //
    //    Expected Data :
    //
    //        {
    //        "title":"Ahmet",
    //        "body":"Merhaba",
    //        "userId":10,
    //        "id":70
    //        }


    @Test
    public void put01() {

        //url ve body
        specJsonPlace.pathParams("pp1", "posts", "pp2", 70);


        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        HashMap<String, Object> reqBody = testDataJsonPlaceHolder.requestBodyOlusturMap();

        System.out.println("reqBody map = " + reqBody);
        // expected data
        HashMap<String, Object> expDataMap = testDataJsonPlaceHolder.requestBodyOlusturMap();

        //response kaydet
        Response response = given().spec(specJsonPlace).contentType(ContentType.JSON).
                when().
                body(reqBody).// artik toString dememe gerekyok. cunku jsonobje javaya ait degildi.ama map oyle
                        put("/{pp1}/{pp2}");


        response.prettyPrint();

        //assertion

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());


        // JsonPath resJP=response.jsonPath();//body için önceden bunu yapıyorduk artık alttaki gibi olacak
        HashMap<String, Object> resMap = response.as(HashMap.class);
        System.out.println(resMap);
        System.out.println(resMap.get("UserId"));//null doner u buyuk yazmısım
        System.out.println(expDataMap.get("userId"));

        Assert.assertEquals(expDataMap.get("title"), resMap.get("title"));
        Assert.assertEquals(expDataMap.get("body"), resMap.get("body"));
        Assert.assertEquals(expDataMap.get("id"), resMap.get("id"));

        Assert.assertEquals(expDataMap.get("userId"), resMap.get("userId"));//u yu buyuk yazmıstım null donup duruyordu


    }
}
