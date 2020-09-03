package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredExtension {
    public static RequestSpecification Request; //static Request

    public RestAssuredExtension(){
        //Arrange
        //RequestSpecBuilder class to get RequestSpecification reference
        RequestSpecBuilder builder=new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000/"); // Setting Base URI
        builder.setContentType(ContentType.JSON); //Setting Content Type
        // Creating request specification using RequestSpecBuilder
        RequestSpecification requestSpec = builder.build(); //Assign RequestSpecBuilder to RequestSpecification
        //Get Request
        Request= RestAssured.given().spec(requestSpec);
    }
    public static void GetReqWithPathParameter(String url, Map<String, String> pathParams) {
        //Act
        Request.pathParams(pathParams);
        try {
            Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static ResponseOptions<Response> GetReq(String url) {
        //Act
        try {
            return Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ResponseOptions<Response> GetReqWithToken(String url, String token) {
        //Act
        try {
            Request.header(new Header("Authorization", "Bearer "+token));
            return Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ResponseOptions<Response> PostReqWithBodyAndPathParams(String url, Map<String,String> pathParam, Map<String,String> body) {
        Request.pathParams(pathParam);
        Request.body(body);
        return Request.post(url);
    }
    public static ResponseOptions<Response> PostReqWithBody(String url, Map<String, String> body){
        Request.body(body);
        return Request.post(url);
    }
    public static ResponseOptions<Response> DeleteReqWithPathParams(String url, Map<String,String> pathParams){
        Request.pathParams(pathParams);
        return Request.delete(url);
    }
    public static ResponseOptions<Response> GetReqWitParams(String url, Map<String, String > pathParams){
        Request.pathParams(pathParams);
        return Request.get(url);
    }
    public static ResponseOptions<Response> GetWithQueryParamsWithToken(String url, Map<String,String> queryParams, String token){
        Request.header(new Header("Authorization", "Bearer "+token));
        Request.queryParams(queryParams);
        return Request.get(url);
    }

    public static ResponseOptions<Response> PutReqWithBodyAndPathParams(String url, Map<String, String> body, Map<String, String> pathParams) {
        Request.pathParams(pathParams);
        Request.body(body);
        return Request.put(url);
    }
}



