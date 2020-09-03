package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import utilities.RestAssuredExtension;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class POSTReqSteps {

    public static ResponseOptions<Response> response;
    @Given("use POST operation for {string}")
    public void usePOSTOperationFor(String arg0) {
        BDDSytlePOSTStep.PostWithBodyParameter();
    }

    @Given("use POST operation for {string} with body")
    public void usePOSTOperationForWithBody(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.cells();

        //Set Data
        HashMap<String, String> body=new HashMap<String, String>();
        body.put("name", data.get(0).get(0)); //first row first column
        System.out.println("body = " + body);

        //Path params
        HashMap<String, String>pathParams=new HashMap<String, String>();
        pathParams.put("profileNo", data.get(0).get(1)); //first row second column
        System.out.println("pathParams = " + pathParams);
        //Perform post operation
        response = RestAssuredExtension.PostReqWithBodyAndPathParams(url, pathParams, body);
    }

    @Then("user should see the body has name as {string}")
    public void userShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"),equalTo(name));
    }

    @Given("Ensure to Perform POST operation for {string} with body as")
    public void ensureToPerformPOSTOperationForWithBodyAs(String url, DataTable table) {
        List<List<String>> data = table.cells();
        Map<String,String> body= new HashMap<>(); //create manual post table
        body.put("id", data.get(1).get(0));
        body.put("title",data.get(1).get(1));
        body.put("author",data.get(1).get(2));

        //Perform Post operation
        RestAssuredExtension.PostReqWithBody(url,body);
    }

    @And("Perform DELETE operation for {string}")
    public void performDELETEOperationFor(String url, DataTable table) {
        List<List<String>> data = table.cells();
        Map<String,String> pathParams= new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));

        //Perform Delete operation
        RestAssuredExtension.DeleteReqWithPathParams(url,pathParams);
    }

    @And("Perform GET operation with path parameter for {string}")
    public void performGETOperationWithPathParameterFor(String url, DataTable table) {

        List<List<String>> data = table.cells();
        Map<String,String> pathParams= new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));

        response= RestAssuredExtension.GetReqWitParams(url, pathParams);
    }

    @And("Perform PUT operation for {string}")
    public void performPUTOperationFor(String url, DataTable table) {
        List<List<String>> data = table.cells();
        Map<String,String> body= new HashMap<>(); //create manual put table
        body.put("id", data.get(1).get(0));
        body.put("title",data.get(1).get(1));
        body.put("author",data.get(1).get(2));

        Map<String,String> pathParams= new HashMap<>();
        pathParams.put("postid",data.get(1).get(0));

        RestAssuredExtension.PutReqWithBodyAndPathParams(url,body, pathParams);
    }

    @Then("user {string} see the body with title as {string}")
    public void userShouldSeeTheBodyWithTitleAs(String condition, String title) {
        if(condition.equalsIgnoreCase("should"))
            assertThat(response.getBody().jsonPath().get("title"), equalTo(title));
        else
            assertThat(response.getBody().jsonPath().get("title"), is(not(equalTo(title))));
//        assertThat(response.getBody().jsonPath().get("title"), is(not(equalTo(title))));  //Should not see the title
//        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
//        Assert.assertNotEquals(response.getBody().jsonPath().get("title"), title);
    }
}
