import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
//import utility.reporting.ExtentManager;
//import utility.reporting.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Commons {

    public static ExtentReports extent;

//    @BeforeSuite
//    public void extentSetup(ITestContext context) {
//        ExtentManager.setOutputDirectory(context);
//        extent = ExtentManager.getInstance();
//    }

//    @BeforeMethod
//    public void startExtent(Method method) {
//        String className = method.getDeclaringClass().getSimpleName();
//        String methodName = method.getName().toLowerCase();
////        ExtentTestManager.startTest(method.getName());
////        ExtentTestManager.getTest().assignCategory(className);
//    }
//
//    protected String getStackTrace(Throwable t) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        t.printStackTrace(pw);
//        return sw.toString();
//    }
//    @AfterMethod
//    public void afterEachTestMethod(ITestResult result) {
////        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
////        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
//
//        for (String group : result.getMethod().getGroups()) {
////            ExtentTestManager.getTest().assignCategory(group);
//        }
//
//        if (result.getStatus() == 1) {
//            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
//        } else if (result.getStatus() == 2) {
//            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
//        } else if (result.getStatus() == 3) {
//            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
//        }
//
//        ExtentTestManager.endTest();
//
//        extent.flush();
//
//        if (result.getStatus() == ITestResult.FAILURE) {
//            captureScreenshot(driver, result.getName());
//        }
//        driver.quit();
//    }

//    @AfterSuite
//    public void generateReport() {
//        extent.close();
//    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static WebDriver driver = null;
    private String saucelabs_username = "brdey";
    private String browserstack_username = "beleedey1";
    private String saucelabs_accesskey = "09d26ec3-c5ca-4a49-b091-ff8471111b32";
    private String browserstack_accesskey = "access-key";

    @Parameters({"useCloudEnv","cloudEnvName","os","os_version","browserName","browserVersion","url"})
    @BeforeClass
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("false")String cloudEnvName,
                      @Optional("Windows") String os,@Optional("10") String os_version, @Optional("firefox") String browserName, @Optional("34")
                              String browserVersion, @Optional("http://www.amazon.com") String url)throws IOException {
        if(useCloudEnv==true){
            if(cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName,browserstack_username,browserstack_accesskey,os,os_version, browserName, browserVersion);
            }else if (cloudEnvName.equalsIgnoreCase("saucelabs")){
                getCloudDriver(cloudEnvName,saucelabs_username, saucelabs_accesskey,os,os_version, browserName, browserVersion);
            }
        }else{
            //run in local
            getLocalDriver(os, browserName);
        }
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        driver.get(url);
    }
    public WebDriver getLocalDriver(@Optional("mac") String OS,String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            if(OS.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.chrome.driver", "../Core/drivers/chromedriver");
            }else if(OS.equalsIgnoreCase("Win")){
                System.setProperty("webdriver.chrome.driver", "../Core/drivers/chromedriver.exe");
            }
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            if(OS.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.gecko.driver", "../Core/drivers/geckodriver");
            }else if(OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "../Core/drivers/geckodriver");
            }
            driver = new FirefoxDriver();

        }
        else if(browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../Generic/driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }
    //Not important but good to know
    public WebDriver getLocalGridDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../Generic/browser-driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }


    //SouceLab or BrowserStack driver setup

    public WebDriver getCloudDriver(String envName,String envUsername, String envAccessKey,String os, String os_version,String browserName,
                                    String browserVersion)throws IOException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser",browserName);
        cap.setCapability("browser_version",browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if(envName.equalsIgnoreCase("Saucelabs")){
            driver = new RemoteWebDriver(new URL("http://"+envUsername+":"+envAccessKey+
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        }else if(envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }
        return driver;
    }

    @AfterClass
    public void closeSession(){
        System.out.println("AfterMethod (Close Browser) was called");
      driver.quit();
    }

    // Selenium void Methods

    public void clickByCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void verifyTitle (String expectedTitle) {
        String actualTitle = driver.getTitle();
        actualTitle.equals(expectedTitle);

    }

    public void verifyURL(String url) {
        String currentURL = driver.getCurrentUrl();
        try {
            url.equals(currentURL);
        } catch (Exception e) {
            System.out.println("Expected Url:" + url + "but current url is: " + currentURL);
        }
    }


    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void typeByCss(String locator, String value) {

        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }
    public void typeByCssNEnter(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }

    public void typeByXpath(String locator, String value) {

        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void takeEnterKeys(String csslocator) {

        driver.findElement(By.cssSelector(csslocator)).sendKeys(Keys.ENTER);
    }

    public void clearInputField(String locator){

        driver.findElement(By.cssSelector(locator)).clear();
    }

    //Return Type Selenium methods
    public List<WebElement> getListOfWebElementsById(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));
        return list;
    }

    public String viewCurrentURL() {
        String currentUrL = driver.getCurrentUrl();
        System.out.println(currentUrL);
        return currentUrL;
    }

    public List<String> getTextFromWebElements(String locator){
        List<WebElement> element = new ArrayList<WebElement>();
        List<String> text = new ArrayList<String>();
        element = driver.findElements(By.cssSelector(locator));
        for(WebElement web:element){
            text.add(web.getText());
        }

        return text;
    }

    public void labelCheckBycss(String locator, String expectedValue){
        try{
            String actualValue = driver.findElement(By.cssSelector(locator)).getText();
            actualValue.equals(expectedValue);
        }
        catch (Exception e){
            String actualValue = driver.findElement(By.cssSelector(locator)).getText();
            System.out.println("Expected Label for locator: " + locator + "is " + expectedValue + "but was " + actualValue);
            throw(e);
        }

    }
    public List<WebElement> getListOfWebElementsByCss(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.cssSelector(locator));
        return list;
    }
    public List<WebElement> getListOfWebElementsByXpath(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.xpath(locator));
        return list;
    }
    public String  getCurrentPageUrl(){
        String url = driver.getCurrentUrl();
        return url;
    }

    public void navigateBack(){
        driver.navigate().back();
    }
    public void navigateForward(){
        driver.navigate().forward();
    }
    public void selectFrameByIndex(String locator){
        driver.switchTo().frame(locator);
    }
    public void closeBrowser () {driver.close();}
    public void closeAllActiveBrowser (){driver.quit();}

    public String getTextByCss(String locator){
        String st = driver.findElement(By.cssSelector(locator)).getText();
        return st;
    }
    public void verifyElementText(String locator, String expectedText) {
        waitUntilVisible(By.cssSelector(locator));
        String actualText = getTextByCss(locator);
        actualText.equals(expectedText);
    }
    public String getTextByXpath(String locator){
        String st = driver.findElement(By.xpath(locator)).getText();
        return st;
    }
    public String getTextById(String locator){
        return driver.findElement(By.id(locator)).getText();
    }
    public String getTextByName(String locator){
        String st = driver.findElement(By.name(locator)).getText();
        return st;
    }

    public List<String> getListOfString(List<WebElement> list) {
        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }
        return items;
    }

    public void selectOptionByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public void sleepFor(int sec)throws InterruptedException{
        Thread.sleep(sec * 1000);
    }
    //overLoading
    public void sleepFor(int min, int sec)throws InterruptedException {
        int time = sec * 1000 + 60 * 1000 * min;
        Thread.sleep(time);
    }

    public void mouseOver(String locator, String locatorType) {
        Actions action = new Actions(driver);
        if (locatorType=="css"){
            WebElement element = driver.findElement(By.cssSelector(locator));
            action.moveToElement(element).perform();
        }
        if (locatorType=="xpath") {
            WebElement element = driver.findElement(By.xpath(locator));
            action.moveToElement(element).perform();
        }
    }
    //handling Alert
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iframeHandle(WebElement element){
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow(){
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName){

        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());;
        }

    }
    //Taking Screen shots
    public void takeScreenShot()throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("screenShots.png"));
    }
    //Synchronization
    public void waitUntilClickAble(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitUntilVisibleAndClickElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitUntilSelectable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    public void upLoadFile(String locator,String path){
        driver.findElement(By.cssSelector(locator)).sendKeys(path);
        /* path example to upload a file/image
           path= "C:\\Users\\rrt\\Pictures\\ds1.png";
         */
    }
    public void clearInput(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }
    public void keysInput(String locator){
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }
    public String converToString(String st){
        String splitString ;
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

}
