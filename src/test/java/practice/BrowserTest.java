package practice;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_object.MainPage;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
@Getter
public class BrowserTest {

    private final String LOCAL_FILE = "file://" + this.getClass().getResource("/elements.html").getPath();
    ChromeDriver driver;

    MainPage mainPage;

    WebDriverWait wait;

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        log.info("Initialize ChromeWebDriver");
        this.driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void chromeDriverTest() {
        Assert.assertEquals(driver.getTitle(), "Google");
//       <title>Google</title>
    }

    @Test
    public void elementTest() {
        driver.get(LOCAL_FILE);
        mainPage = new MainPage(driver);
        mainPage.getFirstNameField().sendKeys("John");
        mainPage.getFirstNameField().clear();
        mainPage.setFirstName("John");
        //mainPage.clickOnMe();
        Assert.assertFalse(mainPage.getStudentCheckBox().isSelected());
        mainPage.selectStudentCheckBox();
        Assert.assertTrue(mainPage.getStudentCheckBox().isSelected());

        for (WebElement element : mainPage.getAllUniversitiesOptions()) {
            System.out.println(element.getText());
        }

        mainPage.getUniversities().selectByValue("RSU");
        Assert.assertEquals(mainPage.getUniversities().getFirstSelectedOption().getText(), "Rīgas Stradiņa universitāte");
        mainPage.getUniversities().selectByVisibleText("Rīgas Tehniskā universitāte");
        Assert.assertEquals(mainPage.getUniversities().getFirstSelectedOption().getText(), "Rīgas Tehniskā universitāte");

        Assert.assertFalse(mainPage.getJavaRadioButton().isSelected());
        mainPage.getJavaRadioButton().click();
        Assert.assertTrue(mainPage.getJavaRadioButton().isSelected());

        mainPage.clickOnMe();
        //wait.until(ExpectedConditions.visibilityOf(mainPage.getClickOnMeResult()));
        wait.until(ExpectedConditions.textToBePresentInElement(mainPage.getClickOnMeResult(), "Viss ir labi!"));
        Assert.assertEquals(mainPage.getClickOnMeResult().getText(), "Viss ir labi!");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        log.info("Closing ChromeWebDriver");
        driver.close();
        driver.quit();
    }
}