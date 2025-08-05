package listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseClass;

public class ListenerImplementation implements ISuiteListener,ITestListener
{
	public ExtentReports report;
	public ExtentSparkReporter spark;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) 
	{
		Reporter.log("Report Configuration", true);
		Date d=new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		spark=new ExtentSparkReporter("./AdvanceReport/report_"+newDate+".html");
		spark.config().setDocumentTitle("Ninza CRM test suite results");
		spark.config().setReportName("Ninza CRM report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Edge");
		
		
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		Reporter.log("Report Backup", true);
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		test=report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO,"====="+result.getMethod().getMethodName()+"Execution Starts=====");
		//Reporter.log("====="+result.getMethod().getMethodName()+"Execution Starts=====", true);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "====="+result.getMethod().getMethodName()+"Success====");
		//Reporter.log("====="+result.getMethod().getMethodName()+"Success====", true);
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String testCaseName = result.getMethod().getMethodName();
		
		Date d=new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts= (TakesScreenshot) BaseClass.ldriver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);//for report
		test.addScreenCaptureFromBase64String(temp,testCaseName+newDate);
		//File temp = ts.getScreenshotAs(OutputType.FILE);
		//File perm=new File("./Screenshots/"+testCaseName+" "+newDate+".png");
//		try {
//			FileHandler.copy(temp, perm);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		test.log(Status.FAIL, "====="+testCaseName+"Failure=====");
		//Reporter.log("====="+testCaseName+"Failure=====", true);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		test.log(Status.SKIP, "====="+result.getMethod().getMethodName()+"Skipped=====");
		//Reporter.log("====="+result.getMethod().getMethodName()+"Skipped=====", true);
		
	}

	
	

}
