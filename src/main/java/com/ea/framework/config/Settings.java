package com.ea.framework.config;

import com.ea.framework.base.BrowserType;
import com.ea.framework.utilities.LogUtil;

import java.sql.Connection;

/**
 * Created by Karthik-PC on 06/01/2018
 */
public class Settings
{
   //For Application Backend
    public static Connection AUTConnection;
    //For EARS Reporting
    public static Connection ReportingConnection;
    //For Application backend connection string
    public static String AUTConnectionString;
    public static String ReportingConnectionString;
    //Log Path for framework
    public static String LogPath;
    //Driver Type for SQL Server connectivity
    public static String DriverType;
    public static String ExcelSheetPath;
    public static String AUT;
    public static  BrowserType BrowserType;
    public static LogUtil Logs;
    public static String SeleniumGridHub;

}
