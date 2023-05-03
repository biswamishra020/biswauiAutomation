package com.ea.framework.base;

import com.ea.framework.config.ConfigReader;
import com.ea.framework.config.Settings;
import com.ea.framework.utilities.CommonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Karthik-PC on 11/23/2016.
 */
public class FrameworkInitialize extends Base {


    public static void InitializeBrowser(BrowserType browserType) throws IOException {
        ConfigReader.PopulateSettings();
        RemoteWebDriver driver; ;
        switch (browserType)
        {
            case Chrome:
            {
                System.out.println("executing in chrome");
               String userdir=System.getProperty("user.dir");
                System.setProperty("webdriver.chrome.driver",userdir+"/driver/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("–ignore-ssl-errors=yes");
//                options.addArguments("platform=Linux");
//                options.addArguments("version=latest");
                options.addArguments("–ignore-certificate-errors");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
               // options.setHeadless(true);
                WebDriverManager.chromedriver().setup();
               // driver=new ChromeDriver(options);
               // System.out.println(Settings.SeleniumGridHub+ "------------");
                String currentIP=CommonUtil.getIpAddress();
                driver= new RemoteWebDriver(new URL("http://192.168.0.161:4444/wd/hub"),options);
                LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                break;
            }
            case Firefox:
            {
                //Open the browser
                System.setProperty("webdriver.gecko.driver", "C:\\chromedriver\\geckodriver.exe");
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options=new FirefoxOptions();

               driver= new RemoteWebDriver(new URL(Settings.SeleniumGridHub),options);
                LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                break;
            }
            case IE:
            {
                break;
            }
            case Headless:
            {
                System.out.println("executing in headless");
                String userdir=System.getProperty("user.dir");
                // System.setProperty("webdriver.chrome.driver",userdir+"/driver/chromedriver");
                ChromeOptions options = new ChromeOptions();

                options.setHeadless(true);
               // WebDriverManager.chromedriver().setup();
                // driver=new ChromeDriver(options);
                System.out.println(Settings.SeleniumGridHub+ "------------");
                driver= new RemoteWebDriver(new URL(Settings.SeleniumGridHub),options);
                LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                break;
            }
            case Safari:
                break;
        }
    }


}
