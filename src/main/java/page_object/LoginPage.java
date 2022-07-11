package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class LoginPage {

    WebDriver driver = WebDriverManager.getInstance();

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public void authorize(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }


}
