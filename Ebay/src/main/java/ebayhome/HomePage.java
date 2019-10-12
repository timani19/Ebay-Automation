package ebayhome;

import base.CommonAPI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage extends CommonAPI {
    @Test(enabled = false)
    public void findUrl() throws InterruptedException {
        System.out.println(getCurrentPageUrl());
        Thread.sleep(5000);
    }

    @Test(enabled = false)
    public void testDropDown() {
        WebElement element = getElement("//select[@id='gh-cat']");
        Select select = new Select(element);
        select.selectByIndex(5);     // it will print out 5th element from the dropdown
        sleepFor(5000);
    }

    @Test(enabled = false)
    public void ebayDropDownByText() {
        WebElement element = getElement("//select[@id='gh-cat']");
        Select select = new Select(element);
        select.selectByVisibleText("Art");  // it will print out 'Art''element from the dropdown
        sleepFor(5000);
    }

    @Test(enabled=false)
    //MouseHover by LinkText
    public void testMouseHover() {
        ebayMouseHover("Fashion");
        clickOnElementByLinkText("Jewelry");
        sleepFor(5);
    }
    // Scroll to view the WebPage using CommonAPI

    @Test(enabled = false)
    public void scrollToViewUsingCommonApi() {
        scrollIntoView("Contact us");
        clickOnElementByLinkText("Contact us");
        sleepFor(10);
    }
    // Scroll to view the page w/o common API method
    @Test(enabled = false)
    public void ebayScrollToView(){
        WebElement element=getElementByLinkText("Registration");
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        clickOnElementByLinkText("Registration");
        sleepFor(5);
    }
    // Another way to scrolling

    @Test(enabled = false)
    public void testScrolling() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,1000)");
        sleepFor(10);
    }
    @Test(enabled = false)
    public void searchDresses(){
        typeOnElementByXpath("//input[@id='gh-ac']", "dresses");
        clickOnElementByXpath("//input[@id='gh-btn']");
        sleepFor(10);
    }

    @Test
    public void searchShoes(){
        typeOnElementByXpath("//input[@id='gh-ac']", "shoes" );
        clickOnElementByXpath("//input[@id='gh-btn']");
    }
}
