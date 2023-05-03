package steps;

import com.ea.framework.base.DriverContext;
import com.ea.framework.base.FrameworkInitialize;
import com.ea.framework.config.ConfigReader;
import com.ea.framework.config.Settings;
import com.ea.framework.utilities.DatabaseUtil;
import com.ea.framework.utilities.ExcelUtil;
import com.ea.framework.utilities.LogUtil;
import com.ea.framework.utilities.ReportingUtil;
import cucumber.api.java.Before;


import java.io.IOException;

/**
 * Created by Karthik-pc on 12/4/2018.
 */
public class TestInitialize extends FrameworkInitialize {


    @Before
    public void Initialize() throws IOException {
        //Initialize Config
        ConfigReader.PopulateSettings();

        //Logging
        Settings.Logs = new LogUtil();
        Settings.Logs.CreateLogFile();
        Settings.Logs.Write("Framework Initialize");

        //Create Test Cycle for Reporting
        Settings.ReportingConnection = DatabaseUtil.Open(Settings.ReportingConnectionString);
        ReportingUtil.CreateTestCycle(Settings.ReportingConnection);

        Settings.Logs.Write("Test Cycle Created");
        InitializeBrowser(Settings.BrowserType);
        Settings.Logs.Write("Browser Initialized");
        DriverContext.GoToUrl(Settings.AUT);
        Settings.Logs.Write("Navigated to URL " + Settings.AUT);

        try {
            ExcelUtil util = new ExcelUtil(Settings.ExcelSheetPath);
        } catch (Exception e) {
        }

    }
}
