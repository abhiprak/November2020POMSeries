package com.qa.realerp.listeners;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestListener implements ITestListener {

//private static final String OUTPUT_FOLDER = "./build/";

//private static final String FILE_NAME = "TestExecutionReport.html";

static Date d = new Date();
static String fileName = "Validation_Report_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

private static ExtentReports extent = createInstance(System.getProperty("user.dir")+"/Reports/"+fileName);

public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

//private static ExtentReports extent = init();
// public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

public static ExtentReports createInstance(String fileName) {

/*
* Path path = Paths.get(OUTPUT_FOLDER); // if directory exists? if
* (!Files.exists(path)) { try { Files.createDirectories(path); } catch
* (IOException e) { // fail to create directory e.printStackTrace(); } }
*/

ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
//htmlReporter.config().setAutoCreateRelativePathMedia(true);
htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
       
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
        extent.setSystemInfo("Automation Tester", "Test1");
        extent.setSystemInfo("Organization", "Test2");
        extent.setSystemInfo("Build no", "xyz");
               
        return extent;
    }

public void onStart(ITestContext context) {
System.out.println("Test Suite started!");

}

public void onFinish(ITestContext context) {
System.out.println(("Test Suite is ending!"));
extent.flush();
testReport.remove();

}

public void onTestStart(ITestResult result) {
String methodName = result.getMethod().getMethodName();
String qualifiedName = result.getMethod().getQualifiedName();
int last = qualifiedName.lastIndexOf(".");
int mid = qualifiedName.substring(0, last).lastIndexOf(".");
String className = qualifiedName.substring(mid + 1, last);

System.out.println(methodName + " started!");
ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
result.getMethod().getDescription());

extentTest.assignCategory(result.getTestContext().getSuite().getName());
/*
* methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
* splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
*
* ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
*/
extentTest.assignCategory(className);
testReport.set(extentTest);
testReport.get().getModel().setStartTime(getTime(result.getStartMillis()));

}

public void onTestSuccess(ITestResult result) {
System.out.println((result.getMethod().getMethodName() + " passed!"));
testReport.get().pass("Test passed");
testReport.get().getModel().setEndTime(getTime(result.getEndMillis()));

}

public void onTestFailure(ITestResult result) {
// System.out.println((result.getMethod().getMethodName() + " failed!"));
// try {
// test.get().fail(result.getThrowable().MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

// } catch (IOException e) {
// System.err
// .println("Exception thrown while updating test fail status " + Arrays.toString(e.getStackTrace()));
// }
// testReport.get().fail("Test Failed");
// testReport.get().getModel().setEndTime(getTime(result.getEndMillis()));

// String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
// testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
// + "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");

String excepionMessage=result.getThrowable().getMessage() + "\n";
excepionMessage.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");

// String issueDescription = result.getThrowable().getMessage()+"\n";
// issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

// try {
//
// ExtentManager.captureScreenshot();
// testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
// MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
// .build());
// } catch (IOException e) {
//
// }


String failureLogg="TEST CASE FAILED";
Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
testReport.get().log(Status.FAIL, m);
}

public void onTestSkipped(ITestResult result) {
System.out.println((result.getMethod().getMethodName() + " skipped!"));
// try {
// test.get().skip(result.getThrowable(),
// MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
// } catch (IOException e) {
// System.err
// .println("Exception thrown while updating test skip status " + Arrays.toString(e.getStackTrace()));
// }
testReport.get().pass("Test skipped");
testReport.get().getModel().setEndTime(getTime(result.getEndMillis()));
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));

}

private Date getTime(long millis) {
Calendar calendar = Calendar.getInstance();
calendar.setTimeInMillis(millis);
return calendar.getTime();
}


}

