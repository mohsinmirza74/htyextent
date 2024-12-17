package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class reports8 {

	public static void main(String[] args) throws IOException {
	    // Initialize ExtentReports
        ExtentReports extentReports = new ExtentReports();
        File file = new File("report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Report Name");
        sparkReporter.config().setReportName("Doc Title");
        sparkReporter.config().setReportName("dd-MM-yyyy hh:mm:ss");
        sparkReporter.config().setCss(".badge-primary {background-color: #6569df}");

        extentReports.attachReporter(sparkReporter);

        extentReports
        .createTest("Test 1" ,"Test2")
         .assignAuthor("Mohsin")
         .assignCategory("smoke ")
         .assignDevice("Chrome99")
         .pass("This is passed test");
   
        
         extentReports
         .createTest("Test 2" ,"Test descnt")
          .assignAuthor("mohisn ")
          .assignCategory("Regression")
          .assignDevice("Chromebroeser")
          .fail("This is failed test");
         
         extentReports
         .createTest("Test 3" ,"Test descnt")
          .assignAuthor("mohisn ")
          .assignCategory("sanity")
          .assignDevice("Chromebroeser")
          .fail("This is failed test");
         
         extentReports
         .createTest("Test 4" ,"Test descnt")
          .assignAuthor("mohisn ")
          .assignCategory("sanity")
          .assignDevice("Chromebroeser")
          .fail("This is a passed test");
         
         extentReports
         .createTest("Test 5" ,"Test descnt")
          .assignAuthor("mohisn, abubakr ,noman ")
          .assignCategory("sanity")
          .assignCategory("Regression ")
          .assignDevice("Mobile device techno spark6")
          .fail("This is a passed test");
        extentReports.flush();
        Desktop.getDesktop().browse(new File("report.html").toURI());

	
}
}

