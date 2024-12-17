package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ExtentReportTest {
    public static void main(String[] args) throws IOException {
        // Create ExtentReports and attach SparkReporter
        ExtentReports extentReports = new ExtentReports();
        File file = new File("report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        extentReports.attachReporter(sparkReporter);

        // Test with log messages
        extentReports
                .createTest("Text Based Test")
                .log(Status.INFO, "Info1")
                .log(Status.INFO, "<b>Info2</b>")
                .log(Status.INFO, "<i>Info3</i>")
                .log(Status.INFO, "<b><i>Info4</i></b>");

        // Properly formatted XML data
        String XMLData = "<menu id=\"file\" value=\"File\">\n" +
                "    <popup>\n" +
                "        <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\n" +
                "        <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\n" +
                "        <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\n" +
                "    </popup>\n" +
                "</menu>";

        // Properly formatted JSON data
        String JsonData = "{\n" +
                "    \"menu\": {\n" +
                "        \"id\": \"file\",\n" +
                "        \"value\": \"File\",\n" +
                "        \"popup\": {\n" +
                "            \"menuitem\": [\n" +
                "                {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
                "                {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
                "                {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        // Add XML-based test
        extentReports
                .createTest("XML Based Test")
                .info(MarkupHelper.createCodeBlock(XMLData, CodeLanguage.XML));

        // Add JSON-based test
        extentReports
                .createTest("JSON Based Test")
                .log(Status.INFO, MarkupHelper.createCodeBlock(JsonData, CodeLanguage.JSON));

        // Generate the report
        extentReports.flush();

        // Open the report in the default browser
        Desktop.getDesktop().browse(file.toURI());
    }
}