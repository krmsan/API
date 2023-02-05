package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D11 {

    //https://jsonplaceholder.typicode.com/posts/22 url'ine
    //    bir GET request yolladigimizda donen response body’sinin
    //    asagida verilen ile ayni oldugunu test ediniz
    //
    //   Response body :
    //    {
    //    "userId":3,
    //    "id":22,
    //    "title":"dolor sint quo a velit explicabo quia nam",
    //    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    //    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    //    }

    @Test
    public void get01(){


        String url="https://jsonplaceholder.typicode.com/posts/22";

        Response response =
                given().when().get(url);
        response.prettyPrint();

        //========================================================================================//

        response.then()
                .assertThat()
                .body("userId", Matchers.equalTo(3),"id",Matchers.equalTo(22),
                        "title",Matchers.equalTo("dolor sint quo a velit explicabo quia nam"),
                        "body",Matchers.containsString("eos qui et ipsum ipsam suscipit"));
        //========================================================================================//
        //========================================================================================//


        JsonPath responsejsonpath=response.jsonPath();//burayı tum body içinden istedigimizi alabilmek için yapıyoruz

        JSONObject expbody=new JSONObject();
        expbody.put("userId", 3);
        expbody.put("id",22);
        expbody.put("title","dolor sint quo a velit explicabo quia nam");
        expbody.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        Assert.assertEquals(expbody.get("userId")
                ,responsejsonpath.get("userId"));

    }
}
