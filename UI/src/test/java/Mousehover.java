import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Mousehover extends Commons {

    @Test
    public void mouseOver() throws InterruptedException {

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

        //Mouse hover on profile
        mouseOver("#menu-item-7123 > a", "css");

        //Click sub-menu
        sleepFor(4);
        driver.findElement(By.linkText("Forums")).click();

        //Log out from main menu
        clickByXpath("//*[@id=\"menu-item-4546\"]/a");
        sleepFor(5);

        viewCurrentURL();
        verifyURL(ObjectRepo.loginURL);

        //go to home page
        clickByCss("#logo > h2 > a > img.boss-logo.large");

        viewCurrentURL();
        verifyURL(ObjectRepo.homeURL);

        closeBrowser();

    }
}

