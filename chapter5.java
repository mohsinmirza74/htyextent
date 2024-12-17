package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class chapter5 {

	private static Status Status;
	private static Media jsonDta;
	private static String CodeLanguge;
	private static Object mapData;
	private static Object mapadata;

	public static void main(String[] args) throws IOException {
		 // Create ExtentReports and attach SparkReporter
        ExtentReports extentReports = new ExtentReports();
        File file = new File("report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        extentReports.attachReporter(sparkReporter);
        extentReports
        .createTest("Text based test")
        .log(Status.INFO, "info1")
        .log(Status.INFO, "<b>info2</b>")
        .log(Status.INFO, "<i>info3<i>")
        .log(Status.INFO, "<i>info4<i></b>");
        
        
        String XMLData = "<menu id=\"file\" value=\"File\">\n" +
                "    <popup>\n" +
                "        <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\n" +
                "        <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\n" +
                "        <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\n" +
                "    </popup>\n" +
                "</menu>";
        
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
     
        extentReports
      .createTest("XML based test")
     .info(MarkupHelper.createCodeBlock(XMLData , CodeLanguage.XML));
        

        extentReports
      .createTest("JSON based test")
      .info(MarkupHelper.createCodeBlock(XMLData , CodeLanguage.JSON));
        
        List<String> listData=new ArrayList<>();
        listData.add("Mohsin");
        listData.add("ali");
        listData.add("Khan");
        
        Map<Integer, String >mapData=new HashMap();
        mapData.put(101,"rashid");
        mapData.put(101,"Mohsin");
        mapData.put(101,"Khan");
        
        
        Set<Integer> SetData=mapData.keySet();
        extentReports
        .createTest("List based test")
         .info(MarkupHelper.createOrderedList(listData))
        .info(MarkupHelper.createUnorderedList(listData));
        
        extentReports
        .createTest("Set based test")
         .info(MarkupHelper.createOrderedList(SetData))
        .info(MarkupHelper.createUnorderedList(SetData));
       
        extentReports
        .createTest("map based test")
      .info(MarkupHelper.createOrderedList(mapData))
        .info(MarkupHelper.createUnorderedList(mapData));
        
        extentReports
        .createTest("Highlight log test")
        .info("this is not highlighted message ")
        .info(MarkupHelper.createLabel("This is highlight",ExtentColor.RED));
        try {
        	int i=5/0;
        }catch(Exception e) {
        	extentReports
        	.createTest("ExceptionTest1")
        	.info(e);
        }
        
        Throwable t=new RuntimeException("This is custom exception");
        extentReports
    	.createTest("Exceptiontest2")
    	.info(t);
		extentReports.flush();
		Desktop.getDesktop().browse(new File ("report.html").toURI());

	}

	
	

}
