package tests;

import PageFactory.Header;
import PageFactory.ModifyProfileModal;
import PageFactory.Homepage;
import PageFactory.LoginPage;
import PageFactory.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ModifyProfile extends TestObject {

    @DataProvider(name = "getUsername")
    public Object[][] getUsernames() {
        return new Object[][]{
                {"iskilloo", "123123aA", "some Public Info"},
        };
    }

    @Test(dataProvider = "getUsername")
    public void testPublicInfoUpdate(String username, String password, String publicInfo) {
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
}
