import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class HomePage extends app_base {


    @Test(priority = 1)
    public void homePageVerify () throws InterruptedException {

        verifyURL(ObjectRepo.homeURL);

        verifyTitle("IconACADEME - Online Education Revolution in Bangladesh");

    }
    @Test(priority = 0)
    public void headerIcon() throws InterruptedException{
        driver.manage().window().fullscreen();

        clickByCss(".boss-logo.large");
        verifyURL(ObjectRepo.homeURL);

        sleepFor(4);

        //Store button
        clickByCss("#menu-item-2348 > a");
        verifyURL("https://www.iconacademe.com/store/");
        verifyTitle("Premium Online Courses and Student Services | IconACADEME Store");

        navigateBack();

        //Course button

        clickByCss(".fa-book");
        verifyURL("https://www.iconacademe.com/courses/");
        verifyTitle("Browse All Courses | IconACADEME");

        navigateBack();


        mouseOver("#menu-item-8901 > a", "css");

        //Click sub-menu
        driver.findElement(By.linkText("Terms and Conditions")).click();


    }
}
