package hooks;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class Hooks {

    WebDriver driver;

    @Before()
    public void before() {
        driver = WebDriverManager.getInstance();
    }
}
