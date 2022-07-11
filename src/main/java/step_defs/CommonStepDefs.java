package step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import page_object.LoginPage;
import utils.WebDriverManager;

public class CommonStepDefs {

    WebDriver driver = WebDriverManager.getInstance();
    LoginPage loginPage = new LoginPage();

    @Given("I have navigate to login page")
    public void navigateToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I login with {string} and {string}")
    public void loginUsingCredentials(String username, String password) {
        loginPage.authorize(username, password);
    }

    @Then("I am successfully logged in")
    public void checkSuccessLogin() {
        Assertions.assertThat(driver.getCurrentUrl()).containsIgnoringCase("inventory.html");
    }
}
