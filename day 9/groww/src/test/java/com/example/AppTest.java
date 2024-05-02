package com.example;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    WebDriver driver;
    JavascriptExecutor js;
    Logger log=LogManager.getLogger(getClass());
    ExtentReports report;
    ExtentSparkReporter spark;
    ExtentTest test;
    
    
    @BeforeTest
    public void setUp() {
        log.info("Before Test");

        driver=new ChromeDriver();
        js=(JavascriptExecutor)driver;
        
        driver.get("https://groww.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        report=new ExtentReports();
        spark=new ExtentSparkReporter("C:\\Users\\prach\\Desktop\\groww\\src\\main\\resources\\report.html");
        report.attachReporter(spark);
        test=report.createTest("Groww Test");

    }
    @Test
    public void shouldAnswerWithTrue()
    {
        js.executeScript("window.scrollBy(0,1000)");

        driver.findElement(By.xpath("//*[@id=\'footer\']/div/div[1]/div/div[1]/div[2]/div[3]/a[2]")).click();

        js.executeScript("window.scrollBy(0,800)");

        driver.findElement(By.xpath("//*[@id=\'root\']/div[2]/div[2]/a[15]/div")).click();

        driver.findElement(By.xpath("//*[@id=\'LOAN_AMOUNT\']")).clear();

        driver.findElement(By.xpath("//*[@id=\'LOAN_AMOUNT\']")).sendKeys("2300000");

        driver.findElement(By.id("RATE_OF_INTEREST")).clear();

        driver.findElement(By.id("RATE_OF_INTEREST")).sendKeys("8");

        driver.findElement(By.id("LOAN_TENURE")).clear();

        driver.findElement(By.id("LOAN_TENURE")).sendKeys("25");

        //Scrolling to the element
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id=\'root\']/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table")));

        List<WebElement> rows=driver.findElements(By.xpath("//*[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table/tr"));

        for(WebElement row:rows)
        {
            List<WebElement> cols=row.findElements(By.tagName("td"));
            for(WebElement col:cols)
            {
                System.out.println(col.getText());
            }
        }
        test.pass("Test Passed");

        //Capture Screenshot in the report
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file,new File("C:\\Users\\prach\\Desktop\\groww\\src\\main\\resources\\screenshot.png"));
            test.addScreenCaptureFromPath("C:\\Users\\prach\\Desktop\\groww\\src\\main\\resources\\screenshot.png");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        assertTrue( true );
    }
    @AfterTest
    public void tearDown() {
        // log.info("After Test");
        driver.quit();
        report.flush();
    }
}
