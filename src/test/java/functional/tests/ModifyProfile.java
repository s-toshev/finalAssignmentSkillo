package functional.tests;

import factory.components.Header;
import factory.components.ModifyProfileModal;
import factory.pages.Homepage;
import factory.pages.LoginPage;
import factory.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ModifyProfile extends TestObject {

    @DataProvider(name = "getUsernames")
    public Object[][] getUsernames(){
        return new Object[][]{
                {"TestSkilloEvelin", "Test123" , "TestEvelin"},
        };
    }

    @DataProvider(name = "getEmails")
    public Object[][] getEmails(){
        return new Object[][]{
                {"TestSkilloEvelin", "Test123" , "eva@test.com"}
        };
    }

    @Test(dataProvider = "getUsernames")
    public void testPublicInfoUpdate (String username, String password, String publicInfo){
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded());
        loginPage.login(username, password);

        Homepage homepage = new Homepage(driver);
        homepage.isUrlLoaded();

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded());
        profilePage.clickModifyProfileButton();

        ModifyProfileModal modifyProfileModal = new ModifyProfileModal(driver);
        modifyProfileModal.updatePublicInfo(publicInfo);
        modifyProfileModal.clickSaveButton();

        profilePage.isUrlLoaded();
        Assert.assertTrue(profilePage.getPublicInfo().contains(publicInfo));

    }

    @Test(dataProvider = "getEmails")
    public void testEmailUpdate(String username, String password, String email) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded());
        loginPage.login(username, password);

        Homepage homepage = new Homepage(driver);
        Assert.assertTrue(homepage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded());
        profilePage.clickModifyProfileButton();

        ModifyProfileModal modifyProfileModal = new ModifyProfileModal(driver);
        modifyProfileModal.updateEmail(email);
        modifyProfileModal.clickSaveButton();

        profilePage.isUrlLoaded();
        profilePage.clickModifyProfileButton();
        Assert.assertTrue(modifyProfileModal.isEmailUpdated().contains(email), "Email is not updated!");

    }

}
