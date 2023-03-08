package tests;

import PageObject.Header;
import PageObject.Homepage;
import PageObject.LoginPage;
import PageObject.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Logout extends TestObject {
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"iskilloo", "123123aA"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogOut(String username, String password) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        Homepage homePage = new Homepage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "Home URL incorrect!");

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "Profile URL not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, username, "Username incorrect!");

        header.clickLogout();
        Assert.assertTrue(loginPage.isUrlLoaded());
    }
}
