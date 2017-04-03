package apache_maven;
import org.junit.*;
import org.openqa.selenium.WebDriver;

/**
 * Created by Administrator on 3/28/2017.
 */
public class Sanity {
    private static WebDriver driver;




    public static void SanityTCs() throws Exception {
        Login();
        SimpleTest();
    }

    @Test
    public static void Login()
    {

        System.out.println("Sanity***********************************************");


    }

    @Test
    public static void SimpleTest() throws Exception {
        System.out.println("SimpleTest");
        driver.get("http://192.168.153.188/ccmweb");
        Thread.sleep(2000);
        //Common.MakeScreen(Thread.currentThread().getStackTrace()[1].getMethodName());
        /*WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium");
        Thread.sleep(1000);
        element.submit();
        Thread.sleep(2000);
        System.out.println("Page title is: " + driver.getTitle());
        assertTrue(driver.getTitle().contains("Selenium")); */



    }
}
