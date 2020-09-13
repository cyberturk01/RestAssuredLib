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
        public static String createMyJsonFile(int i){
        ExcelUtil data=new ExcelUtil("src/test/resources/TestData.xlsx", "returnService");
        List<Map<String,String>> myExcel=data.getDataList();
        return  "{\n"+
                " \"portalUuid\": \""+ myExcel.get(i).get("portalUuid")+ "\", \n" +
                " \"meta\": { \n" +
                " \"languageCode\": \""+ myExcel.get(i).get("languageCode")+ "\", \n" +
                " \"returnReason\": \""+ myExcel.get(i).get("returnReason")+ "\", \n" +
                " \"orderNumber\": \""+ myExcel.get(i).get("orderNumber")+ "\" \n"+
                "}, \n"+
                " \"sender\": {  \n"+
                " \"fullName\": \""+ myExcel.get(i).get("fullName")+ "\", \n"+
                " \"addressStreet\": \""+ myExcel.get(i).get("addressStreet")+ "\", \n"+
                " \"addressCountryCode\": \""+ myExcel.get(i).get("addressCountryCode")+ "\", \n"+
                " \"addressZipCode\": \""+ myExcel.get(i).get("addressZipCode")+ "\", \n"+
                " \"addressCity\": \""+ myExcel.get(i).get("addressCity")+ "\", \n"+
                " \"email\": \""+ myExcel.get(i).get("email")+ "\" \n" +
                "}"+
                "}";
    }
}
