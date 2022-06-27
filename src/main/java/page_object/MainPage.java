package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameField = By.id("fNameID");
    private By clickOnMeButton = By.xpath("//button[@id='checkDataID']");
    private By clickOnMeResult = By.id("checkDataResultID");
    private By studentCheckBox = By.xpath("//input[@id='studentID' and @type='checkbox']");
    private By universitiesList = By.name("universities");
    private By javaRadioButton = By.id("javaID");

    public WebElement getFirstNameField() {
        return driver.findElement(firstNameField);
    }

    public void setFirstName(String value) {
        driver.findElement(firstNameField).sendKeys(value);
    }

    public void clickOnMe() {
        driver.findElement(clickOnMeButton).click();
    }

    public void selectStudentCheckBox() {
        driver.findElement(studentCheckBox).click();
    }

    public WebElement getStudentCheckBox() {
        return driver.findElement(studentCheckBox);
    }

    public List<WebElement> getAllUniversitiesOptions() {
        return new Select(driver.findElement(universitiesList)).getOptions();
    }

    public Select getUniversities() {
        return new Select(driver.findElement(universitiesList));
    }

    public WebElement getJavaRadioButton() {
        return driver.findElement(javaRadioButton);
    }

    public WebElement getClickOnMeResult() {
        return driver.findElement(clickOnMeResult);
    }
}
