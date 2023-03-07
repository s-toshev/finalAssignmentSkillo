package functional.tests;

import object.components.Header;
import object.components.PostModal;
import object.pages.Homepage;
import object.pages.LoginPage;
import object.pages.NewPostPage;
import object.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;

public class CreateNewPost extends TestObject{

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {

        File postPicture = new File("src/test/resources/upload/testimage.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{
                {"TestSkilloEvelin", "Test123", postPicture, caption},
        };
    }

    @Test(dataProvider = "getUsers")
    public void testPostCreation (String username, String password, File file, String caption) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        Homepage homePage = new Homepage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is incorrect!");

        Header header = new Header(driver);
        header.clickNewPost();

        NewPostPage postPage = new NewPostPage(driver);
        Assert.assertTrue(postPage.isUrlLoaded(), "The POST URL is incorrect!");
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");
        postPage.populatePostCaption(caption);
        postPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption);
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}

