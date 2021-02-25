package com.jossmain.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static String driverPath = "src/resources/drivers/";
    public static String DEVELOPMENT_URL = "https://www.jossandmain.com";
    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @Parameters({"browser"}) // Use parameters "browser" for TestNG.xml
    @BeforeMethod(alwaysRun = true)
    /** Add @Optional to use Browser as default for run Test of Methods in the classes */
    public void openBrowser(@Optional("chrome") String browser) throws IOException {

        switch (browser) {
            case "chrome":
                /* Create Chrome WebDriver (control version WebDriver with Current version of Browser) */
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver_88.exe");
                driver = new ChromeDriver();
                // Disable message Info-bars argument
                ChromeOptions optionChrome = new ChromeOptions();
                optionChrome.addArguments("disable-infobars");
                break;

 /*         case "firefox":
                *//** Create Firefox WebDriver (control version WebDriver with Current version of Browser) *//*
                System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                driver = new FirefoxDriver();
                // Disable message Info-bars argument
                FirefoxOptions optionFirefox = new FirefoxOptions();
                optionFirefox.addArguments("disable-infobars");
                break;

            /** case "IE":
             // Create Internet Explorer Driver WebDriver (see realise to
             // use ... Start > Settings > System > About > OS Build)
             System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer_3.9.0");
             driver = new InternetExplorerDriver();
             break;

            /** case "edge":
             // Create EdgeDriver WebDriver (see realise to use ...
             // Start > Settings > System > About > OS Build)
             System.setProperty("webdriver.edge.driver", driverPath + "MicrosoftWebDriver17134");
             driver = new EdgeDriver();
             break; */


            /** Run default Chrome browser in case others will not work for TestNGsuite.xml files */
            default:
                System.out.println("Do not know how to start " + browser + ", starting Chrome instance");
                // Create Chrome WebDriver
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver_88.exe");
                driver = new ChromeDriver();
                // Disable message Info-bars argument
                ChromeOptions optionDefault = new ChromeOptions();
                optionDefault.addArguments("disable-infobars");
                break;
        }

        /********************************* SETUP ADDITIONAL PARAMETERS FOR ALL BROWSERS *******************************/
        // Setup maximum size window
        driver.manage().window().maximize();
        // Wait web page load respond max 20 sec
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        // Wait elements on web page max 20 seс
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.navigate().to(DEVELOPMENT_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() throws IOException, InterruptedException {
        // Close All Web Browser the windows
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // Close All Web Browser the windows
        driver.quit();
    }

}
