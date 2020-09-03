package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojos.Address;
import pojos.Location;
import pojos.LoginBody;
import pojos.Posts;
import utilities.APIConstant;
import utilities.RestAssuredExtension;
import utilities.RestAssuredUtilitiesV2;

import javax.xml.crypto.Data;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.hasItem;


public class GETReqSteps {
    String Url = "http://localhost:3000/posts/";
    String token="response.getBody().jsonPath().get(\"access_token\")";
    public static ResponseOptions<Response> response;

    @Given("use GET operation for {string}")
    public void useGETOperationFor(String url) {
        response = RestAssuredExtension.GetReq(url);
    }

    @Then("user should see the author name as {string}")
    public void userShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        Posts response = GETReqSteps.response.getBody().as(Posts.class);
        assertThat(response.getAuthor(), is(equalTo(authorName)));
//        assertThat(response.getBody().jsonPath().getList("author"), hasItem(authorName));
//        assertThat(GETReqSteps.response.getBody().jsonPath().getList("id"), hasItem(2));

//        List<String> list = GETReqSteps.response.getBody().jsonPath().getList("author");
//        System.out.println(GETReqSteps.response.getBody().jsonPath().getString("author[0]"));
//        assertThat(GETReqSteps.response.getBody().jsonPath().getString("author[0]"), is(authorName));
//        System.out.println(list);
//        assertThat(list, hasItem(authorName));
    }

    @Then("user should see the author names")
    public void userShouldSeeTheAuthorNames() {
        BDDStyleGetStep.PerformContainsCollection();
    }

    @Then("user should see verify GET parameter")
    public void userShouldSeeVerifyGETParameter() {
        BDDStyleGetStep.PerformPathParameter();
    }

    @Then("user should see verify GET queryParameter")
    public void userShouldSeeVerifyGETQueryParameter() {
        BDDStyleGetStep.PerformQueryParameter();
    }

    @Given("Perform Authentication operation for {string} with body")
    public void performAuthenticationOperationForWithBody(String uri, DataTable data) {
        List<List<String>> cells = data.cells();
//        Map<String, String> body = new HashMap<>();
//        body.put("email", cells.get(1).get(0));
//        body.put("password", cells.get(1).get(1));
        LoginBody loginBody=new LoginBody();
        loginBody.setEmail(cells.get(1).get(0));
        loginBody.setPassword(cells.get(1).get(1));

        RestAssuredUtilitiesV2 restAssuredUtilitiesV2= new RestAssuredUtilitiesV2(uri, APIConstant.ApiMethods.POST, token);
        restAssuredUtilitiesV2.Authenticate(loginBody);

//        response=RestAssuredExtension.PostReqWithBody(url, body);
    }

    @Given("Perform GET operation for with Token {string}")
    public void performGETOperationForWithToken(String url) {
        response=RestAssuredExtension.GetReqWithToken(url,token);
    }


    @And("I perform GET operation with path parameter for address {string}")
    public void iPerformGETOperationWithPathParameterForAddress(String uri, DataTable table) {
        List<List<String>> data = table.cells();
        Map<String, String> queryParams= new HashMap<>();
        queryParams.put("id",data.get(1).get(0));
        RestAssuredUtilitiesV2 restAssuredUtilitiesV2=new RestAssuredUtilitiesV2(uri, "GET", token);
        response = restAssuredUtilitiesV2.ExecuteWithQueryParams(queryParams);
//        response=RestAssuredExtension.GetWithQueryParamsWithToken(url,queryParams,token);
    }

    @Then("I should see the street name as {string}")
    public void iShouldSeeTheStreetNameAs(String streetName) {

        Location []location = response.getBody().as(Location[].class);
        Address address = location[0].getAddress()
                .stream()
                .filter(x->x.getType().equalsIgnoreCase("primary"))
                .findFirst()
                .orElse(null);
        assertThat(address.getStreet(),is(equalTo(streetName)));
    }
}
