package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class BDDStyleGetStep {
    static String url="http://localhost:3000/posts/";

    public static void SimpleGetPost(String postNumber){
        given().contentType(ContentType.JSON)
                .when().get(url+postNumber)
                .then().body("author",is("Gokhan"))
                .statusCode(200);
    }
    public static void PerformContainsCollection(){
        given()
                .contentType(ContentType.JSON)
        .when()
                .get(url)
        .then()
                .body("author", containsInAnyOrder("Gokhan","Nuran", "Hale", null, "Zeynep"))
                .statusCode(200);
    }
    public static void PerformPathParameter(){
        given()
                .contentType(ContentType.JSON)
        .with()
                .pathParam("post", 3)
        .when()
                .get(url+"{post}")
        .then()
                .body("author",containsString("Hale"))
                .statusCode(200);
    }
    public static void PerformQueryParameter(){
        given()
                .contentType(ContentType.JSON)
        .with()
                .queryParam("id", 2)
        .when()
                .get(url).prettyPeek()
        .then()
                .body("author",hasItem("Nuran"))
                .statusCode(200);
    }
}
