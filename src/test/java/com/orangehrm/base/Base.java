package com.orangehrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;
import com.orangehrm.utils.CommonUtilities;

public class Base implements ITestListener {

	public static WebDriver driver;

	public static Map<String,String> locatordata=new HashMap<String,String>();
	public static Map<String,String> testdata=new HashMap<String,String>();
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest exlogger;
	
	public static void main(String[] args) {
		System.out.println(getLocatorDataFromMap("Login", "Uname_Box"));
	}

	@BeforeMethod(alwaysRun = true)
	public static void launchBrowser() throws Exception {
	
		String url= getConfigData("url");
		String browser=getConfigData("browser");
		
	
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./src/test/utilities/chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./src/test/utilities/geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	
	public static String getConfigData(String key) throws Exception {
		
		File f = new File("./src/test/data/config.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties prop= new Properties();
		prop.load(fis);
		
		String value= prop.getProperty(key);
		return value;
		
		
	
		
	}
	
	/*
	public static String getLocatorData( String pageName, String elementName) throws Exception {
		File f = new File("./data/elementpaths.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("paths");
		int rows = ws.getLastRowNum();
		
		String locator="";
		
		for(int i =1; i<=rows; i++) {
			
			String page = ws.getRow(i).getCell(0).getStringCellValue();
			String element = ws.getRow(i).getCell(1).getStringCellValue();
			
			if(page.equalsIgnoreCase(pageName) && element.equalsIgnoreCase(elementName)) {
				
				 locator= ws.getRow(i).getCell(2).getStringCellValue();	
			}
		}
		return locator;
	}
	*/

	public static void storeLocatorData() throws Exception {
		File f = new File("./src/test/data/elementpaths.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("paths");
		int rows=ws.getLastRowNum();
		
		for(int i =1; i<=rows;i++) {
			
		String page = ws.getRow(i).getCell(0).getStringCellValue();
		String element = ws.getRow(i).getCell(1).getStringCellValue();
		String locator=ws.getRow(i).getCell(2).getStringCellValue();
		
		locatordata.put(page+"$"+element, locator);
			
		}
	}
	
	public static String getLocatorDataFromMap(String pageName, String elementName) {
		String value = locatordata.get(pageName+"$"+elementName);
		return value;
	}
	
	
	/*
	
	public static String getTestData(String pageName, String elementName) throws Exception {
		File f = new File("./data/testvalues.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("data");
		int rows=ws.getLastRowNum();
		String value="";
		for(int i=1; i<=rows; i++) {
			
			String page =ws.getRow(i).getCell(0).getStringCellValue();
			String element=ws.getRow(i).getCell(1).getStringCellValue();
			
			if(page.equalsIgnoreCase(pageName) && element.equalsIgnoreCase(elementName)){
				
				  value=ws.getRow(i).getCell(2).getStringCellValue();
			}
		}
		return value;
	}
	*/
	
	public static void storeTestData() throws Exception 
	{
			File f = new File("./src/test/data/testvalues.xlsx");
			FileInputStream fis = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws=wb.getSheet("data");
			int rows=ws.getLastRowNum();
			
			for(int i=1;i<=rows;i++)
				{
					String page=ws.getRow(i).getCell(0).getStringCellValue();
					String element=ws.getRow(i).getCell(1).getStringCellValue();
					String value= ws.getRow(i).getCell(2).getStringCellValue();
					testdata.put(page+"$"+element, value);
				}	
	}
	
	public static String getTestDataFromMap(String pageName, String elementName) {
		String testvalue = testdata.get(pageName+"$"+elementName);
		return testvalue;
	}
	
	public static void writeResultsToFile(String testcasename, String testcasestatus) throws Exception {
		File f = new File("./src/test/results/results.txt");
		FileWriter fw = new FileWriter(f, true);
		fw.write(testcasename+"______"+testcasestatus+"\r\n");
		fw.flush();
		fw.close();
		
		
	}
	
	public static void captureScreenshots(String filename) throws Exception {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest= new File("./src/test/results/screenshots/"+filename+".png");
		Files.copy(src, dest);		
		
	}
	
	
	/*public static void extentReports() {
		File f = new File("./src/test/results/results.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("f");
		ExtentReports ExRp= new ExtentReports();
		ExRp.attachReporter(htmlReporter);
		ExtentTest reportName = ExRp.createTest("Sample Test");
		
		reportName.log(Status.INFO, "Starting the test case Sample");
		reportName.log(Status.INFO,"\successfully created the user...");
		reportName.log(Status.PASS, "The sample test case Passed");
		ExRp.flush();
		
	}*/
	
	@AfterMethod(alwaysRun = true)
	public static void closeBrowser() {
		driver.close();
	}


	public void onFinish(ITestContext arg0) {
		System.out.println("the execution has completed");
	}


	public void onStart(ITestContext arg0) {
		File f1 = new File("./src/test/results/report.html");
		reporter = new ExtentHtmlReporter(f1);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		exlogger = extent.createTest("Initialization Steps");
		
		try {
			storeLocatorData();
			storeTestData();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		File f = new File("./src/test/results/results.txt");
		
		
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(" Starting the fresh Execution "+"\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailure(ITestResult arg0) {
		String testcasename = arg0.getName();
		try {
			writeResultsToFile(testcasename ,"Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestStart(ITestResult arg0) {
		
		
	}

	
	public void onTestSuccess(ITestResult arg0) {
		String testcasename = arg0.getName();
		try {
			writeResultsToFile(testcasename ,"pass");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
