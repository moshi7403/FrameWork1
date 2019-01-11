import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class InvalidLogin extends Commons {

    @Override
    public void verifyElementText(String locator, String expectedText) {
        super.verifyElementText(locator, expectedText);
        waitUntilVisible(By.className(locator));
        String actualText = getTextByCss(locator);
        actualText.equals(expectedText);
    }


        @Test
        public void loginCheck() throws InterruptedException {
            verifyTitle("IconACADEME - Online Education Revolution in Bangladesh");

            //Click Login button
            clickByCss("a.login");
            verifyURL(ObjectRepo.loginURL);

            //UserID input
            typeByCss("input#user_login", "test");

            //UserPassword input
            typeByCss("input#user_pass", "test123");


            //Click Submit button
            clickByCss("#wp-submit");
            sleepFor(2);

            // Verify Error Message
            verifyElementText(".login-msg", " Invalid username and/or password.");


            //Log out from main menu
            clickByXpath("//*[@id=\"menu-item-4546\"]/a");
            sleepFor(5);

            viewCurrentURL();
            verifyURL(ObjectRepo.loginURL);

            //go to home page
            clickByCss("#logo > h2 > a > img.boss-logo.large");
            sleepFor(2);

            viewCurrentURL();
            verifyURL(ObjectRepo.homeURL);

            closeBrowser();

        }
    }


