package apache_maven;
import apache_maven.AppTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 3/28/2017.
 */
public class Common extends AppTest {
    public static ScreenshotHelper screenshotHelper;
    private static WebDriver driver;

    public static void Login()
    {
        System.out.println("login________________________________________________");

    }



public static class ScreenshotHelper {
    public void saveScreenshot(String screenshotFileName, WebDriver driver) throws IOException {
        System.out.println("Make screenshot");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("Screenshots/" + screenshotFileName + ".png"));
    }
}


}
