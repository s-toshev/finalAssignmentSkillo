package object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();
    }

    public void clickModifyProfileButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modifyProfile = driver.findElement(By.xpath("//h2/following-sibling::i"));
        wait.until(ExpectedConditions.elementToBeClickable(modifyProfile));
        modifyProfile.click();
    }
//    public boolean isPublicInfoUpdated(publicInfo) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Boolean isPublicInfoDisplayed = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//app-profile")));
//        //Assert.assertTrue(isPublicInfoDisplayed, "The text differs!");
//        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
//    }

    public String getPublicInfo() {
        WebElement actualPublicInfo = driver.findElement(By.xpath("//div[@class='col-12']//p[1]"));
        return actualPublicInfo.getText();
    }
}