package apache_maven;


import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.io.FileNotFoundException;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.util.calendar.BaseCalendar;


/**
 * Unit test for simple App.
 */
public class AppTest {
    private static ScreenshotHelper screenshotHelper;
    public static WebDriver driver;
    public static Properties prop;

    public static void LoadConfigFile() {
        try
        {
            FileInputStream fileInput = new FileInputStream(new File("Config.xml"));
            prop = new Properties();
            prop.load(fileInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public ErrorCollector collector = new ErrorCollector();



    @BeforeClass
    public static void openBrowser() {
        LoadConfigFile();
        System.out.println("start driver");
        //firefox
        //System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
        //driver = new FirefoxDriver();

        //chrome
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        screenshotHelper = new ScreenshotHelper();
        driver.manage().window().maximize();
    }

    @Ignore
    @Test
    public void IncorrectLoginTest() throws Exception
    {
        try {
            LogoutUser();
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
            //driver.get("https://192.168.152.26");
            WebElement login = driver.findElement(By.id("username_vis"));
            login.sendKeys(prop.getProperty("IncorrectLogin"));
            WebElement pass = driver.findElement(By.id("password_vis"));
            pass.sendKeys(prop.getProperty("IncorrectPassword"));
            pass.submit();
            WebElement IncPass = driver.findElement(By.id("err_msg_str"));
            assertTrue(IncPass != null);
            Passed(Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (NoSuchElementException e){
            screenshotHelper.saveScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName(), driver);
            assertTrue(false);
        }
    }

    @Test
    public void LoginTest() throws Exception
    {
        try {
            LogoutUser();
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
            //driver.get("https://192.168.152.26");
            LoginUser();
            WebElement username = driver.findElement(By.className("displayable_username"));
            assertTrue(username != null);
            LogoutUser();
            Passed(Thread.currentThread().getStackTrace()[1].getMethodName());
        }catch (NoSuchElementException e) {
            screenshotHelper.saveScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName(), driver);
            assertTrue(false);
        }
    }


    public void LogoutUser() throws Exception
    {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        driver.get("http://" + prop.getProperty("IPaddress"));
        try {
            WebElement username = driver.findElement(By.className("displayable_username"));
            if (username.isDisplayed()) {
                WebElement LogOut = driver.findElement(By.linkText("Log out"));
                LogOut.click();
                //driver.get("http://" + prop.getProperty("IPaddress")+"/logout.cgi");

                System.out.println("User logouted");
                assertTrue(driver.findElement(By.linkText("log back in")) != null);
            }
        }catch (NoSuchElementException e) {
            System.out.println("User is not login");
        }
    }

    public void LoginUser() throws Exception
    {
        try {
            WebElement username = driver.findElement(By.className("displayable_username"));
            if (username.isDisplayed()){
                System.out.println("User already logined");
            }

        }catch (NoSuchElementException e) {
            WebElement login = driver.findElement(By.id("username_vis"));
            login.sendKeys(prop.getProperty("Login"));
            WebElement pass = driver.findElement(By.id("password_vis"));
            pass.sendKeys(prop.getProperty("Password"));
            pass.submit();
        }
    }

    public void Passed(String TestName)
    {
        System.out.println("****************************************");
        System.out.println(TestName + " - Passed");
        System.out.println("****************************************");
    }

    @AfterClass
    public static void saveScreenshotAndCloseBrowser() throws IOException {
        driver.quit();
    }

    private static class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName, WebDriver driver) throws IOException {
            System.out.println("Make screenshot");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("Screenshots/" + screenshotFileName + ".png"));
        }
    }


}
    	


