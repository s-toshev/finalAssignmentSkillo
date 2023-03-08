package tests;

import PageObject.Header;
import PageObject.PostModal;
import PageObject.Homepage;
import PageObject.LoginPage;
import PageObject.NewPostPage;
import PageObject.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class newPost extends TestObject {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {

        File postPicture = new File("src/test/resources/upload/IMG.jpg");
        String caption = "Create post caption test";

        return new Object[][]{
                {"iskilloo", "123123aA", postPicture, caption},
        };
    }

    @Test(dataProvider = "getUsers")
    public void testPostCreation(String username, String password, File file, String caption) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        Homepage homePage = new Homepage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "Home URL incorrect!");

        Header header = new Header(driver);
        header.clickNewPost();

        NewPostPage postPage = new NewPostPage(driver);
        Assert.assertTrue(postPage.isUrlLoaded(), "Post URL incorrect!");
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "Image not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "Image name incorrect!");
        postPage.populatePostCaption(caption);
        postPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "Profile URL not correct!");
        Assert.assertEquals(profilePage.getPostCount(), 1, "The Number of Posts incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "Image not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption);
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}

