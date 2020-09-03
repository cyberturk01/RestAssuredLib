package steps;

import gherkin.deps.com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BDDSytlePOSTStep {
    static String url="http://localhost:3000/posts/";
    public static void PostWithBodyParameter(){
        Map<String, String> postContent= new LinkedHashMap<String, String>();
        postContent.put("id","9");
        postContent.put("title","API Assured course");
        postContent.put("author","Nuran Y.");

        Gson gson=new Gson(); //convert to gson obj
        String content = gson.toJson(postContent);
        given()
                .contentType(ContentType.JSON)
        .with()
                .body(content)
        .when()
                .post(url)
        .then()
                .body("author", Is.is("Nuran Y.")); //check if body has item
    }
    static String authUrl="http://localhost:3000/auth/login";
    public static void authGeneration(){
        Map<String, String> pass=new HashMap<>();
        pass.put("email","yigitgokhan1@gmail.com");
        pass.put("password","abc123");

        given()
                .contentType(ContentType.JSON)
        .with()
                .body(pass)
        .when()
                .get(url);


    }
}
