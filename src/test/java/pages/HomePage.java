package pages;

import com.ea.framework.base.BasePage;
import com.ea.framework.base.DriverContext;
import com.ea.framework.controls.elements.HyperLink;
import com.ea.framework.report.ExtentReport;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(RemoteWebDriver driver)

    {
        super();

        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.LINK_TEXT, using = "Login")
    public WebElement lnkLogin;

    @FindBy(how = How.LINK_TEXT, using = "Employee List")
    public WebElement lnkEmployeeList;

    @FindBy(how = How.XPATH, using = "//a[@title='Manage']")
    public WebElement lnkUserName;



    public void ClickLogin() {
        System.out.println("hiiii login");
        lnkLogin.click();
        ExtentReport.test.pass("login clicked");
        //return GetInstance(LoginPage.class);
    }

    public boolean IsLogin() {
        return lnkLogin.isDisplayed();
    }

    public String GetLoggedInUser() {
        return lnkUserName.getText();
    }

    public EmployeeListPage ClickEmployeeList() {
        DriverContext.WaitForPageToLoad();
        DriverContext.WaitForElementVisible(lnkEmployeeList);
        lnkEmployeeList.click();
        return GetInstance(EmployeeListPage.class);
    }
}
