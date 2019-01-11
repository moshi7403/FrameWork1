import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;


public class StoreButton extends app_base {



    @Test
    public void storeButton() throws InterruptedException {


        clickByCss("a.fa-th");
        clickByXpath("//*[@id=\"post-2307\"]/div/section/div[2]/div/div/div/div[2]/ul/li[2]/a[1]/img");
        sleepFor(4);
        navigateBack();

        //DropDown menu test
        Select ddList = new Select(driver.findElement(By.name("product_cat")));
        ddList.selectByValue("it-courses");
        sleepFor(2);
        goBackToHomeWindow();



    }
}
