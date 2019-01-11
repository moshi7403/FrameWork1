import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.io.IOException;

public class ReadfromExcel extends app_base {
    private String suiteExcelFile="/Users/tzaman/Workspace/IconAcademe/UI/src/test/resources/TestData.xlsx";
    public void clearInputBoxByCss(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }
    public void sendKeysByCssThenEnter(String locator, String Text){
        driver.findElement(By.cssSelector(locator)).sendKeys(Text, Keys.ENTER);
    }


    public void readfromExcel() throws IOException, InterruptedException {
        DataRead dataRead = new DataRead(suiteExcelFile);
        int length = dataRead.getRowCount("Sheet1");
        for (int i = 2; i <= length; i++) {
            clearInputBoxByCss("#lst-ib.gsfi");
            sendKeysByCssThenEnter("#lst-ib.gsfi", dataRead.getCellData("Sheet1", "Search Item",i));
            sleepFor(2);

        }

    }
    @Test(priority = 0)
    public void googleSearch() throws InterruptedException, IOException {
        driver.navigate().to("https://www.google.com");
        DataRead dataRead = new DataRead(suiteExcelFile);
        int length = dataRead.getRowCount("Sheet1");
        for (int i = 2; i <= length; i++) {
            clearInputBoxByCss("#lst-ib.gsfi");
            sendKeysByCssThenEnter("#lst-ib.gsfi", dataRead.getCellData("Sheet1", "Search Item", i));
            sleepFor(2);

        }
    }
}
