import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import java.io.IOException;

public class Purchase extends app_base {


    @Test(priority = 0)
    public void navigateToStore() throws InterruptedException {
        navitageToStoreFromHome();
    }
    @Test(priority = 1)
    public void addToCart() throws InterruptedException {
        addToCartbyCoupon();
    }
    @Test(priority = 2)
            public void checkOut() throws InterruptedException {

        typeByCss("#coupon_code", "ceodiscount");

        clickByCss("#post-587 > div > div:nth-child(2) > div > div > div > div > form > table > tbody > tr:nth-child(2) > td > div > input.button");

        clickByCss("#post-587 > div > div:nth-child(2) > div > div > div > div > div > div > div > a");

        clickByCss("#post-588 > div > div:nth-child(2) > div > div > div > div > div > a");

        typeByCss("#username", "test");

        typeByCss("#password", "test1234");


        clickByCss("#post-588 > div > div:nth-child(2) > div > div > div > div > form.login > p:nth-child(5) > input.button");

        sleepFor(3);

        clickByCss("#terms");

        clickByCss("#place_order");

        sleepFor(15);
    }
    @Test(priority = 3)
            public void verifyEnrollment() throws InterruptedException {

        waitUntilVisible(By.cssSelector("#left-menu-toggle"));

        clickByCss("#left-menu-toggle");
        waitUntilVisibleAndClickElement(By.cssSelector("\"#menu-item-2071 > a\""));
//        waitUntilClickAble(By.cssSelector("#menu-item-2071 > a"));
//        clickByCss("#menu-item-2071 > a");
        verifyElementText("#course-114-5849 > h4 > a", "CCNA Accelerated");
    }
    @Test(priority = 13)
            public void logout() throws InterruptedException {

        //Logout
        mouseOver("//*[@id=\"masthead\"]/div[1]/div/div/div[3]/div/div[3]/a/span[2]/img", "xpath");
        clickByXpath("//*[@id=\"masthead\"]/div[1]/div/div/div[3]/div/div[3]/div/span/a");

    }
    @Test(priority = 14)
            public void verifyHomepage() throws InterruptedException{
        //go to home page
        clickByCss("#logo > h2 > a > img.boss-logo.large");

        viewCurrentURL();
        verifyURL(ObjectRepo.homeURL);


    }

}