package object.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ModifyProfileModal {

    private final WebDriver driver;
    private final WebElement modalElement;

    public ModifyProfileModal(WebDriver driver) {
        this.driver = driver;
        this.modalElement = driver.findElement(By.className("modal-content"));
    }

    public void changeUsername(String newUsername) {
            WebElement username = modalElement.findElement(By.xpath("//input[@formcontrolname=\"email\"]"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(username)).isEnabled();
            username.clear();
            username.sendKeys(newUsername);
    }

    public void updatePublicInfo (String publicInfo) {
        WebElement publicInfoField = modalElement.findElement(By.xpath("//textarea[@formcontrolname=\"publicInfo\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(publicInfoField)).isEnabled();
        publicInfoField.clear();
        publicInfoField.sendKeys(publicInfo);

    }

    public void clickSaveButton () {
        WebElement saveButton = modalElement.findElement(By.xpath("//button[@type=\"submit\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).isEnabled();
        saveButton.click();
    }

    public void updateEmail (String email) {
        WebElement emailField = modalElement.findElement(By.xpath("//input[@formcontrolname=\"email\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(emailField)).isEnabled();
        emailField.clear();
        emailField.sendKeys(email);

    }

    public String isEmailUpdated() {
        WebElement actualEmail = driver.findElement(By.xpath("//input[@formcontrolname='email']"));
        return actualEmail.getText();
    }
}
