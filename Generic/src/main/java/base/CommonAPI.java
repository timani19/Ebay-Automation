package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
    // Browser Setup
    public static WebDriver driver = null;

    // We will call this url param in the test runner class
    @Parameters({"url"})

    // Before running the test system will execute following code. This setup will automate the webpage

    @BeforeMethod
    public void setUp(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tanim Imani\\IdeaProjects\\WebAutomation\\Generic\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }
    // After executing a test, it will close the browser

    @AfterMethod
    public void cleanUp() {
        driver.close();
        driver.quit();
    }

    // Thread.sleep will pause the execution for milliseconds. We will use this to see the result of the test cases. No need to add this when doing real automation

    public void sleepFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Common Methods
    // Below methods will go to the locator given and perform click

    public static void clickOnElementByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public static void clickOnElementByCSS(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public static void clickOnElementById(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public static void clickOnElementByName(String locator) {
        driver.findElement(By.name(locator)).click();
    }

    public static void clickOnElementByLinkText(String locator) {
        driver.findElement(By.linkText(locator)).click();
    }

    //typeOnElement methods

    public static void typeOnElementByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public static void typeOnElementByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public static void typeOnElementById(String locator, String value) {
        driver.findElement(By.id(locator)).sendKeys(value);
    }

    public static void typeOnElementByName(String locator, String value) {
        driver.findElement(By.name(locator)).sendKeys(value);
    }

    public boolean isElementDisplayed(String locator) {
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    public boolean isElementEnabled(String locator) {
        boolean flag = true;
        flag = driver.findElement(By.xpath(locator)).isEnabled();
        return flag;
    }

    public boolean isElementSelected(String locator) {
        boolean flag = true;
        flag = driver.findElement(By.xpath(locator)).isSelected();
        return flag;
    }

    /**
     * @param locator - xpath that we are trying to make webElement of
     * @return WebElement - WebElement of the xpath
     */

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public WebElement getElementByLinkText(String locator) {
        return driver.findElement(By.linkText(locator));
    }
    // We use drag and drop method in case we need to drag something from one location and drop it in another
    // Always use Actions class, also have to use .build().perform() method

    public void dragNDropByXpaths(String fromLocator, String toLocator) {
        Actions actions = new Actions(driver);
        WebElement from = getElement(fromLocator);
        WebElement to = getElement(toLocator);
        actions.dragAndDrop(from, to).build().perform();
    }

    // We can use this method to scroll through the Webpage
    public void scrollIntoView(String locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", getElementByLinkText(locator));
    }

    //OR
    /* public void scrollIntoView(String locator) {
    WebElement element=getElementByLinkText(locator)
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true), element);
    }
     */


    // Capture Screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            //FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/" + screenshotName + " " + df.format(date) + ".png"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
            ;
        }

    }

    public String getCurrentPageUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void getText(String locator) {
        String st;
        try {
            st = driver.findElement(By.cssSelector(locator)).getText();

        } catch (Exception ex1) {

            try {
                st = driver.findElement(By.xpath(locator)).getText();

            } catch (Exception ex2) {

                try {
                    st = driver.findElement(By.name(locator)).getText();

                } catch (Exception ex3) {

                    st = driver.findElement(By.id(locator)).getText();
                }
            }
        }
    }

    // Mouse Hover method

    public void mouseHoverByCSS(String locator) {
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }
    }

    public void mouseHoverByXpath(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }
    }
    // MouseHover by linkText

    public void ebayMouseHover(String locator) {
        WebElement element = getElementByLinkText(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    //handling Alert
    public void okAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iFrameHandle(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow() {
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator) {
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    //Taking Screen shots
    public void takeScreenShot() throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(file, new File("screenShots.png"));
    }

    //Synchronization
    public void waitUntilClickAble(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilSelectable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    // To upload a file
    public void upLoadFile(String locator, String path) {
        driver.findElement(By.cssSelector(locator)).sendKeys(path);
        /* path example to upload a file/image
           path= "C:\\Users\\rrt\\Pictures\\ds1.png";
         */
    }

    public void clearInput(String locator) {
        driver.findElement(By.cssSelector(locator)).clear();
    }


    //Handling New Tabs
    public static WebDriver handleNewTab(WebDriver driver1) {
        String oldTab = driver1.getWindowHandle();
        List<String> newTabs = new ArrayList<String>(driver1.getWindowHandles());
        newTabs.remove(oldTab);
        driver1.switchTo().window(newTabs.get(0));
        return driver1;
    }

    public static boolean isPopUpWindowDisplayed(WebDriver driver1, String locator) {
        boolean value = driver1.findElement(By.cssSelector(locator)).isDisplayed();
        return value;
    }

    public void typeOnInputBox(String locator, String value) {
        try {
            driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex1) {
            System.out.println("ID locator didn't work");
        }
        try {
            driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex2) {
            System.out.println("Name locator didn't work");
        }
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex3) {
            System.out.println("CSS locator didn't work");
        }
    }
}


