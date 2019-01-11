import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class LogoutFromSettingOption extends Commons {

    @Test
    public void logoutFromSetting() throws InterruptedException {

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

        //Expand left pane
        clickByCss("#left-menu-toggle");

        //click
        mouseOver("#menu-item-7123 > a", "css");

        //Click Sub-menu
        sleepFor(4);
        driver.findElement(By.linkText("Forums")).click();

        //Log out from main menu
        clickByXpath("//*[@id=\"menu-item-4546\"]/a");
        sleepFor(5);

        //Verify Login Url
        viewCurrentURL();
        verifyURL(ObjectRepo.loginURL);

        //go to home page
        clickByCss("#logo > h2 > a > img.boss-logo.large");
        sleepFor(2);

        //Verify Home Url
        viewCurrentURL();
        verifyURL(ObjectRepo.homeURL);

        closeBrowser();

    }
}

