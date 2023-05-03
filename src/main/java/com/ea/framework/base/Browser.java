package com.ea.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Karthik-PC on 11/23/2018.
 */
public class Browser extends Base{


    private WebDriver _driver;

    public Browser(WebDriver driver) {
        _driver = driver;
    }

    public BrowserType Type;



    public void Maximize()
    {
        _driver.manage().window().maximize();
    }

}



