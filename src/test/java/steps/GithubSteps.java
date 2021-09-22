package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;

public class GithubSteps {
    private String api;
    private String nonExistentUser;
    private int githubResponseCode;

    @Given("^github user profile api$")
    public void githubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    @And("^a random non-existent username$")
    public void aRandomNonExistentUsername() {
        nonExistentUser = "A";
    }

    @When("^I look for the random user via the api$")
    public void iLookForTheRandomUserViaTheApi() throws IOException {
        githubResponseCode = getGithubUserProfile(api, nonExistentUser)
                .getStatusLine()
                .getStatusCode();
    }

    @Then("^github respond: (.*)$")
    public void githubRespondNotFound(int arg0) {
        Assert.assertEquals(githubResponseCode, arg0);
    }

    @When("^I look for (.*) via the api$")
    public void iLookForEugenpViaTheApi(String user) throws IOException {
        githubResponseCode = getGithubUserProfile(api, user)
                .getStatusLine()
                .getStatusCode();
    }

    static HttpResponse getGithubUserProfile(String api, String username) throws IOException {
        HttpUriRequest request = new HttpGet(String.format(api, username));
        return HttpClientBuilder
                .create()
                .build()
                .execute(request);
    }
}
