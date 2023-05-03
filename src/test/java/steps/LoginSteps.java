package steps;

import com.ea.framework.base.Base;

import cucumber.api.DataTable;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

/**
 * Created by Karthik-pc on 12/4/2018.
 */
public class LoginSteps extends Base {

    @And("^I ensure application opened$")
    public void iEnsureApplicationOpened() {
        CurrentPage = GetInstance(HomePage.class);
        Assert.assertTrue("The login page is not loaded", CurrentPage.As(HomePage.class).IsLogin());

    }


    @Then("^I click login link$")
    public void iClickLoginLink() {
        //Navigation to Login Page
        //CurrentPage = CurrentPage.As(HomePage.class).ClickLogin();
    }

    @When("^I enter UserName and Password$")
    public void iEnterUserNameAndPassword(DataTable data) {
        List<List<String>> table = data.raw();
        CurrentPage.As(LoginPage.class).Login(table.get(1).get(0).toString(), table.get(1).get(1).toString());
    }

    @Then("^I click login button$")
    public void iClickLoginButton() throws InterruptedException {
        //Home Page
        CurrentPage = CurrentPage.As(LoginPage.class).ClickLogin();
    }

    @Then("^I should see the username with hello$")
    public void iShouldSeeTheUsernameWithHello() throws Throwable {
        Assert.assertEquals("The user is not admin", "Hello admin!", CurrentPage.As(HomePage.class).GetLoggedInUser());
    }


}
