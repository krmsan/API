package tests;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testsData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class D18 extends JsonPlaceHolderBaseUrl {




    /*

  https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
  request yolladigimizda donen response’in status kodunun 200 ve
  response body’sinin asagida verilen ile ayni oldugunu test ediniz

   Response body :
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */




    @Test
    public void get01(){
        //url
        specJsonPlace.pathParams("pp1","posts","pp2",22);


        //expected data hazirla
        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        JSONObject expData=testDataJsonPlaceHolder.expectedBodyOlusturJSON();

        //response u kaydet
        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        JsonPath resJPath=response.jsonPath();//NOT: bu kısım body için diger seyleri response uzerinden alabilirsin.

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());//NOT u oku

        Assert.assertEquals(expData.get("userId"),resJPath.get("userId"));
        Assert.assertEquals(expData.get("id"),resJPath.get("id"));
        Assert.assertEquals(expData.get("title"),resJPath.get("title"));
        Assert.assertEquals(expData.get("body"),resJPath.get("body"));
    }
}
