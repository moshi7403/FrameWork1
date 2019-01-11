
import org.testng.annotations.Test;

public class LoginAndLogout extends Commons {


    @Test
    public void loginAndlogout() throws InterruptedException {
        verifyTitle("IconACADEME - Online Education Revolution in Bangladesh");

        //Click Login button
        clickByCss("a.login");
        verifyURL(ObjectRepo.loginURL);

        //UserID input
        typeByCss("input#user_login", "test");

        //UserPassword input
        typeByCss("input#user_pass", "test1234");


        //Click Submit button
        clickByCss("#wp-submit");
        sleepFor(5);

        //Logout
        mouseOver("//*[@id=\"masthead\"]/div[1]/div/div/div[3]/div/div[3]/a/span[2]/img", "xpath");
        clickByXpath("//*[@id=\"masthead\"]/div[1]/div/div/div[3]/div/div[3]/div/span/a");

        //Verify login Url
        viewCurrentURL();
        verifyURL(ObjectRepo.loginURL);

        //go to home page
        clickByCss("#logo > h2 > a > img.boss-logo.large");
        sleepFor(2);

        //Verify home URL
        viewCurrentURL();
        verifyURL(ObjectRepo.homeURL);

        closeBrowser();

    }
}
