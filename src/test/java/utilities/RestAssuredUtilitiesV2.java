package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredUtilitiesV2 {

    private RequestSpecBuilder builder= new RequestSpecBuilder();
    private String method;
    private String url;

    /**
     *
     * @param uri
     * @param method
     * @param token
     */
    public RestAssuredUtilitiesV2(String uri,String method, String token){
         //Formulate the API url
        this.url= "http://localhost:3000"+uri;
        this.method=method;
        if(token!=null){
            builder.addHeader("Authorization", "Bearer "+token);
        }
    }
    private ResponseOptions<Response> ExecuteAPI(){
        RequestSpecification requestSpecification=builder.build();
        RequestSpecification request= RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);
        if(this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.PUT))
            return request.put(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        else
            return null;
    }
    public String Authenticate(Object body){
        builder.setBody(body);
        return ExecuteAPI().getBody().jsonPath().get("access_token"); // it will be change according to actual tokens
    }
    public ResponseOptions<Response> ExecuteWithQueryParams(Map<String,String> queryParams){
        builder.addQueryParams(queryParams);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithPathParams(Map<String,String> pathParams){
        builder.addPathParams(pathParams);
        return ExecuteAPI();
    }
    public ResponseOptions<Response> ExecuteWithPathParamsAndBody(Map<String,String> pathParams, Map<String,String> body){
        builder.addPathParams(pathParams);
        builder.setBody(body);
        return ExecuteAPI();
    }
}
