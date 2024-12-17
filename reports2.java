package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class reports2 {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports=new ExtentReports();
		File file =new File ("report.html");
		ExtentSparkReporter SparkReporter=new ExtentSparkReporter(file);
		extentReports.attachReporter(SparkReporter);
		
		extentReports
		.createTest("Test 1")
	    .log(Status.INFO,  "info1")
		.log(Status.SKIP,  "skip")
		.log(Status.WARNING,  "warning")
		.log(Status.PASS,  "pass")
		.log(Status.FAIL,  "fail");

	Fail
	Pass
	Skip
	Warning
	info

	
		extentReports.flush();
		Desktop.getDesktop().browse(new File ("report.html").toURI());
	

	}
}
