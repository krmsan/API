package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D04 {





    //  https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
    //        Json formatindaki body ile bir PUT request gonderdigimizde
    //                {
    //                "title":"Ahmet",
    //                "body":"Merhaba",
    //                "userId":10,
    //                "id":70
    //                }
    //        donen Response’un,
    //
    //            status code’unun 200,
    //            ve content type’inin application/json; charset=utf-8,
    //            ve Server isimli Header’in degerinin cloudflare,
    //            ve status Line’in HTTP/1.1 200 OK





    @Test
    public void put01(){

        String url="https://jsonplaceholder.typicode.com/posts/70";
        Response response= given().when().get(url);
        System.out.println(response.contentType());
        String kayıt=response.asPrettyString();
        System.out.println(kayıt);
        response.prettyPrint();




        JSONObject jsonObject= new JSONObject();
        jsonObject.put("title","Ahmet");
        jsonObject.put("body","yeni");
        jsonObject.put("userId",7);
        jsonObject.put("id",70);
        jsonObject.put("isim","aaa");

        //response=given().contentType("application/json; charset=utf-8").when().body(jsonObject.toString()).put(url);
        response=given().contentType(ContentType.JSON).when().body(jsonObject.toString()).put(url);
        response.prettyPrint();


        response.then().assertThat().
                statusCode(200).contentType("application/json").
                header("server","cloudflare").statusLine("HTTP/1.1 200 OK").
                body("title", Matchers.equalTo("Ahmet")).
                body("isim",Matchers.equalTo("aaa")).
                body("userId", Matchers.equalTo(7));






    }
}
