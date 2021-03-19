import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Canvas_Object_Firefox {
    FirefoxDriver driver;


    @Before
    public void setDriver() throws InterruptedException {
        /** Type the path webdriver of Chrome browser
         *  e.g."<D:\\SeleniumRobot\\chromedriver.exe>"*/
        System.setProperty("webdriver.chrome.driver", "<Your\\...\\path\\of\\Chrome\\webdriver>");


        driver = new FirefoxDriver();
        /** Here type correct URL of website under test    */
        String url = "<https://place.to/write/your/url>";


        driver.manage().window().maximize();
        driver.get(url);

        TimeUnit.SECONDS.sleep(15);
    }


    @Test

    public void Test_A_Screenshot() throws Exception {
        /** Type the path and name of the screenshot which
         *  will taken after click interaction
         *  e.g. "C:\\BrowsersCrossTest\\ScreenshotFromTest.png"    */
        takeSnapShot(driver, "<Your\\...\\path\\ScreenshotAfterAction.png>");

    }

    public static void takeSnapShot(WebDriver driver, String fileWithPath) throws Exception {

        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

    @Test
    public void Test_B_Click() throws Exception {


        WebElement canvasFirst = driver.findElement(By.id("#canvas"));

        int width = canvasFirst.getSize().getWidth();
        int height = canvasFirst.getSize().getHeight();

        Actions FirstClick = new Actions(driver);
        /** Type the position coordinate for the place which will be click  */
        FirstClick.moveByOffset(0000, 0000).click().build().perform();


        TimeUnit.SECONDS.sleep(15);
        /** Type the path and name of the screenshot which
         *  will taken after click interaction
         *  e.g. "C:\\BrowsersCrossTest\\ScreenshotFromTest.png"    */
        takeSnapShot2(driver, "<Your\\...\\path\\ScreenshotAfterAction.png>");

    }

    public static void takeSnapShot2(WebDriver driver, String fileWithPath) throws Exception {

        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

    @Test

    public void Test_C_ReadSettings() {


        String currentUrl = driver.getCurrentUrl();

        System.out.println(currentUrl);

        DesiredCapabilities caps = null;
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        assert false;
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new FirefoxDriver();
    }

    @After

    public void ConsoleError () throws Exception {


        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        logEntries.getAll().stream().map(LogEntry::toString).collect(Collectors.toList());
        List<String> lines = logEntries.getAll().stream().map(LogEntry::toString).collect(Collectors.toList());

        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        WebElement canvas = driver.findElement(By.id("#canvas"));

        int width2 = canvas.getSize().getWidth();
        int height2 = canvas.getSize().getHeight();


        String currentUrl = driver.getCurrentUrl();
        /** Type the path and name of the .txt file which will be the generated
         *  with DevTool log from Browser
         *  e.g. "C:\\BrowsersCrossTest\\DevTool_Log.txt"   */
        FileWriter textFile = new FileWriter("<Your\\...\\path\\LogFile.txt>");

        textFile.write(width2 + "" + "Width");
        textFile.write("\n");
        textFile.write("\n");
        textFile.write(height2 + " " + "Height");
        textFile.write("\n");
        textFile.write("\n");
        textFile.write(currentUrl);
        textFile.write("\n");
        textFile.write("\n");
        textFile.write(String.valueOf(lines));

        textFile.close();
        System.out.println("Successfully wrote to the file.");

        driver.quit();
    }

}
