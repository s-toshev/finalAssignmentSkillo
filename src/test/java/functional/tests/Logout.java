package functional.tests;

import object.components.Header;
import object.pages.Homepage;
import object.pages.LoginPage;
import object.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Logout extends TestObject {
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"TestSkilloEvelin", "Test123"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogOut(String username, String password) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        Homepage homePage = new Homepage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is incorrect!");

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, username, "The username is incorrect!");

        header.clickLogout();
        Assert.assertTrue(loginPage.isUrlLoaded());
    }
}
