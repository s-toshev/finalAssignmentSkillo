package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModifyProfileModal {

    private final WebDriver driver;

    @FindBy(xpath = "//input[@formcontrolname=\"username\"]")
    private WebElement username;
    @FindBy(xpath = "//textarea[@formcontrolname=\"publicInfo\"]")
    private WebElement publicInfoField;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement saveButton;
    @FindBy(xpath = "//input[@formcontrolname=\"email\"]")
    private WebElement emailField;

    public ModifyProfileModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void changeUsername(String newUsername) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(username)).isEnabled();
        username.clear();
        username.sendKeys(newUsername);
    }

    public void updatePublicInfo (String publicInfo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(publicInfoField)).isEnabled();
        publicInfoField.clear();
        publicInfoField.sendKeys(publicInfo);
    }

    public void clickSaveButton () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).isEnabled();
        saveButton.click();
    }

    public void updateEmail (String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(emailField)).isEnabled();
        emailField.clear();
        emailField.sendKeys(email);
    }

    public String isEmailUpdated() {
        return emailField.getText();
    }
}
