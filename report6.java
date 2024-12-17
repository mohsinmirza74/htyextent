package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class report6 {
	 static WebDriver driver;
	private static ExtentTest test3;

	    public static void main(String[] args) throws IOException {
	        // Initialize ExtentReports
	        ExtentReports extentReports = new ExtentReports();
	        File file = new File("report.html");
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);

	        extentReports.attachReporter(sparkReporter);

	        // Setup WebDriver and launch the browser
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.get("https://www.google.com/");
	        driver.manage().window().maximize();

	        // Capture screenshot as Base64 and file path
	        String base64Code = captureScreenshotAsBase64();
	        String path = captureScreenshot("GoogleScreenshot");

	        // Add screenshots to Extent Report
	        ExtentTest test1 = extentReports
	                .createTest("Screenshot Test 1", "Attach screenshot as Base64 string")
	                .info("This is an info message");
	        test1.addScreenCaptureFromBase64String(base64Code, "Google Screenshot - Base64");

	        ExtentTest test2 = extentReports
	                .createTest("Screenshot Test 2", "Attach screenshot using file path")
	                .info("This is another info message");
	        test2.addScreenCaptureFromPath(path, "Google Screenshot - File Path");
	        
	        ExtentTest test3 = extentReports
	                .createTest("Screenshot Test 2", "Attach screenshot using file path")
	                .info("This is another info message");
	        test3.addScreenCaptureFromPath(path, "Google Screenshot - File Path");

	        // Generate the report and open it
	        extentReports.flush();
	        Desktop.getDesktop().browse(new File("report.html").toURI());

	        // Close the browser
	        driver.quit();
	    }

	    // Method to capture screenshot as Base64 string
	    public static String captureScreenshotAsBase64() {
	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        return takesScreenshot.getScreenshotAs(OutputType.BASE64);
	    }

	    // Method to capture screenshot and save it as a file
	    public static String captureScreenshot(String fileName) {
	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	        File destFile = new File("./Screenshots/" + fileName + ".png");

	        try {
	            FileUtils.copyFile(sourceFile, destFile);
	            System.out.println("Screenshot saved successfully at: " + destFile.getAbsolutePath());
	        } catch (IOException e) {
	            System.err.println("Failed to save screenshot: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return destFile.getAbsolutePath();


}
}
