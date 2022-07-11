package practice;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_object.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j
public class BrowserTest {

    private final String LOCAL_FILE = "file://" + this.getClass().getResource("/elements.html").getPath();
    RemoteWebDriver driver;
    MainPage mainPage;

    WebDriverWait wait;

    @BeforeTest
    public void setProperties() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() throws MalformedURLException {
        log.info("Initialize ChromeWebDriver / RemoteWebDriver");
        // this.driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(""), new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void chromeDriverTest() {
        //Assert.assertEquals(driver.getTitle(), "Google");
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

    @Test
    public void testTab() {
        driver.get(LOCAL_FILE);
        driver.findElement(By.linkText("LinkedIn")).click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        System.out.println();
    }

    @Test
    public void javascriptTest() {
        driver.get("https:///www.lu.lv");
        WebElement kontakti = driver.findElement(By.linkText("Kontakti"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", kontakti);

        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1024)");
        System.out.println("123");
    }

    @Test
    public void actionTest() {
        driver.get(LOCAL_FILE);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("aboutMeID")))
                .keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND)
                .keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND)
                .keyDown(Keys.COMMAND).sendKeys("v").sendKeys("1").keyUp(Keys.COMMAND).perform();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        log.info("Closing ChromeWebDriver");
        driver.close();
        driver.quit();
    }
}