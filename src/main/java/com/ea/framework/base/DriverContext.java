package com.ea.framework.base;

import com.ea.framework.config.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by Karthik-PC on 21/04/2018.
 */
public class DriverContext {



    public static void GoToUrl(String url)
    {
        LocalDriverContext.getRemoteWebDriver().get(url);
    }

    public static void maximizeBrowser()
    {
        LocalDriverContext.getRemoteWebDriver().manage().window().maximize();
    }

    public static void WaitForPageToLoad(){
        var wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(30));
        var jsExecutor = LocalDriverContext.getRemoteWebDriver();

        ExpectedCondition<Boolean> jsLoad = webDriver ->  (LocalDriverContext.getRemoteWebDriver())
                .executeScript("return document.readyState").toString().equals("complete");

        //Get JS Ready
        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if(!jsReady)
            wait.until(jsLoad);
        else
            Settings.Logs.Write("Page is ready !");

    }

    public  static  void WaitForElementVisible(final WebElement elementFindBy){
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(elementFindBy));
    }

    public static void WaitForElementTextVisible(final WebElement elementFindBy, String text){
        WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    public static void WaitUntilTextDisplayed(final By element, String text){
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(),Duration.ofSeconds(30));
        wait.until(textDisplayed(element, text));
    }

    private static ExpectedCondition<Boolean> textDisplayed (final By elementFindBy, final String text){
        return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
    }

    public static void WaitElementEnabled(final By elementFindBy){
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(),Duration.ofSeconds(30));
        wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
    }


}
