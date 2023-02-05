package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D10 {



    //http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
    //            donen Response'in
    //            status code'unun 200,
    //            ve content type'inin application/json,
    //            ve response body'sindeki
    //                employees sayisinin 24
    //                ve employee'lerden birinin "Ashton Cox"
    //                ve girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu test edin
    //            test edin.
    @Test
    public void get01(){

        // 1 - URL  hazirla

        String url = "http://dummy.restapiexample.com/api/v1/employees";


        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet
        Response response=given().when().get(url);
        //response.prettyPrint();

        //NOT: jsonpath.com a git data.[id] yapıstır 24 satır var diyor.

        //assert
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("status",Matchers.equalTo("success"),
                        "data.id", Matchers.hasSize(24),
                        "data.employee_name",Matchers.hasItem("Ashton Cox"),
                        "data.employee_age",Matchers.hasItems(61,40,30)).
                body("message",Matchers.equalTo( "Successfully! All records has been fetched."));



        /*
        /Users/mac/Library/Java/JavaVirtualMachines/openjdk-19.0.2/Contents/Home/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=53802:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar:/Applications/IntelliJ IDEA CE.app/Contents/plugins/junit/lib/junit5-rt.jar:/Applications/IntelliJ IDEA CE.app/Contents/plugins/junit/lib/junit-rt.jar:/Users/mac/IdeaProjects/API01/target/test-classes:/Users/mac/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/mac/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/mac/.m2/repository/io/rest-assured/rest-assured/4.4.0/rest-assured-4.4.0.jar:/Users/mac/.m2/repository/org/codehaus/groovy/groovy/3.0.8/groovy-3.0.8.jar:/Users/mac/.m2/repository/org/codehaus/groovy/groovy-xml/3.0.8/groovy-xml-3.0.8.jar:/Users/mac/.m2/repository/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar:/Users/mac/.m2/repository/org/apache/httpcomponents/httpcore/4.4.13/httpcore-4.4.13.jar:/Users/mac/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/Users/mac/.m2/repository/commons-codec/commons-codec/1.11/commons-codec-1.11.jar:/Users/mac/.m2/repository/org/apache/httpcomponents/httpmime/4.5.13/httpmime-4.5.13.jar:/Users/mac/.m2/repository/org/hamcrest/hamcrest/2.1/hamcrest-2.1.jar:/Users/mac/.m2/repository/org/ccil/cowan/tagsoup/tagsoup/1.2.1/tagsoup-1.2.1.jar:/Users/mac/.m2/repository/io/rest-assured/json-path/4.4.0/json-path-4.4.0.jar:/Users/mac/.m2/repository/org/codehaus/groovy/groovy-json/3.0.8/groovy-json-3.0.8.jar:/Users/mac/.m2/repository/io/rest-assured/rest-assured-common/4.4.0/rest-assured-common-4.4.0.jar:/Users/mac/.m2/repository/io/rest-assured/xml-path/4.4.0/xml-path-4.4.0.jar:/Users/mac/.m2/repository/org/apache/commons/commons-lang3/3.11/commons-lang3-3.11.jar:/Users/mac/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/2.3.3/jakarta.xml.bind-api-2.3.3.jar:/Users/mac/.m2/repository/jakarta/activation/jakarta.activation-api/1.2.2/jakarta.activation-api-1.2.2.jar:/Users/mac/.m2/repository/com/sun/xml/bind/jaxb-impl/2.3.3/jaxb-impl-2.3.3.jar:/Users/mac/.m2/repository/org/json/json/20160810/json-20160810.jar:/Users/mac/.m2/repository/org/testng/testng/7.6.1/testng-7.6.1.jar:/Users/mac/.m2/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/Users/mac/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/Users/mac/.m2/repository/com/beust/jcommander/1.82/jcommander-1.82.jar:/Users/mac/.m2/repository/org/webjars/jquery/3.6.0/jquery-3.6.0.jar com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 tests.D10,get01
{
    "status": "success",
    "data": [
        {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        },
        {
            "id": 2,
            "employee_name": "Garrett Winters",
            "employee_salary": 170750,
            "employee_age": 63,
            "profile_image": ""
        },
        {
            "id": 3,
            "employee_name": "Ashton Cox",
            "employee_salary": 86000,
            "employee_age": 66,
            "profile_image": ""
        },
        {
            "id": 4,
            "employee_name": "Cedric Kelly",
            "employee_salary": 433060,
            "employee_age": 22,
            "profile_image": ""
        },
        {
            "id": 5,
            "employee_name": "Airi Satou",
            "employee_salary": 162700,
            "employee_age": 33,
            "profile_image": ""
        },
        {
            "id": 6,
            "employee_name": "Brielle Williamson",
            "employee_salary": 372000,
            "employee_age": 61,
            "profile_image": ""
        },
        {
            "id": 7,
            "employee_name": "Herrod Chandler",
            "employee_salary": 137500,
            "employee_age": 59,
            "profile_image": ""
        },
        {
            "id": 8,
            "employee_name": "Rhona Davidson",
            "employee_salary": 327900,
            "employee_age": 55,
            "profile_image": ""
        },
        {
            "id": 9,
            "employee_name": "Colleen Hurst",
            "employee_salary": 205500,
            "employee_age": 39,
            "profile_image": ""
        },
        {
            "id": 10,
            "employee_name": "Sonya Frost",
            "employee_salary": 103600,
            "employee_age": 23,
            "profile_image": ""
        },
        {
            "id": 11,
            "employee_name": "Jena Gaines",
            "employee_salary": 90560,
            "employee_age": 30,
            "profile_image": ""
        },
        {
            "id": 12,
            "employee_name": "Quinn Flynn",
            "employee_salary": 342000,
            "employee_age": 22,
            "profile_image": ""
        },
        {
            "id": 13,
            "employee_name": "Charde Marshall",
            "employee_salary": 470600,
            "employee_age": 36,
            "profile_image": ""
        },
        {
            "id": 14,
            "employee_name": "Haley Kennedy",
            "employee_salary": 313500,
            "employee_age": 43,
            "profile_image": ""
        },
        {
            "id": 15,
            "employee_name": "Tatyana Fitzpatrick",
            "employee_salary": 385750,
            "employee_age": 19,
            "profile_image": ""
        },
        {
            "id": 16,
            "employee_name": "Michael Silva",
            "employee_salary": 198500,
            "employee_age": 66,
            "profile_image": ""
        },
        {
            "id": 17,
            "employee_name": "Paul Byrd",
            "employee_salary": 725000,
            "employee_age": 64,
            "profile_image": ""
        },
        {
            "id": 18,
            "employee_name": "Gloria Little",
            "employee_salary": 237500,
            "employee_age": 59,
            "profile_image": ""
        },
        {
            "id": 19,
            "employee_name": "Bradley Greer",
            "employee_salary": 132000,
            "employee_age": 41,
            "profile_image": ""
        },
        {
            "id": 20,
            "employee_name": "Dai Rios",
            "employee_salary": 217500,
            "employee_age": 35,
            "profile_image": ""
        },
        {
            "id": 21,
            "employee_name": "Jenette Caldwell",
            "employee_salary": 345000,
            "employee_age": 30,
            "profile_image": ""
        },
        {
            "id": 22,
            "employee_name": "Yuri Berry",
            "employee_salary": 675000,
            "employee_age": 40,
            "profile_image": ""
        },
        {
            "id": 23,
            "employee_name": "Caesar Vance",
            "employee_salary": 106450,
            "employee_age": 21,
            "profile_image": ""
        },
        {
            "id": 24,
            "employee_name": "Doris Wilder",
            "employee_salary": 85600,
            "employee_age": 23,
            "profile_image": ""
        }
    ],
    "message": "Successfully! All records has been fetched."
}

Process finished with exit code 0

         */
    }
}
