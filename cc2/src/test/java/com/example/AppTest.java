package com.example;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    String path="D:\\My projects\\cc2\\src\\main\\java\\com\\example\\resources\\excel\\data.xlsx";
    String path1="D:\\My projects\\cc2\\src\\main\\java\\com\\example\\resources\\excel\\report.html";
    WebDriver driver;
    XSSFSheet sheet;
    String authorName="";
    Actions action;
    JavascriptExecutor js;
    WebDriverWait wait;
    Logger log;
    ExtentReports extentReports;
    ExtentTest test;


    @BeforeTest
    public void setUp() {
        System.out.println("Before Test");

        //Set the path of the ChromeDriver
        driver = new ChromeDriver();

        //Create an object of WebDriverWait class to wait for elements to load
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //Open browser
        driver.get("https://www.barnesandnoble.com/");

        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(path1);
        extentReports.attachReporter(spark);
        log = LogManager.getLogger(getClass());


        //Maximize browser
        driver.manage().window().maximize();

        //Create an object of FileInputStream class to read excel file
        try{
            FileInputStream file=new FileInputStream(new File(path));
    
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Row row=workbook.getSheetAt(0).getRow(0);
            authorName=row.getCell(0).getStringCellValue();
            System.out.println(authorName);
            workbook.close();
            file.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        action = new Actions(driver);
        js = (JavascriptExecutor) driver;

    }
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException
    {   
        System.out.println("Test 1");
        wait.until(driver -> driver.findElement(By.linkText("All")));
        driver.findElement(By.linkText("All")).click();
        
        wait.until(driver -> driver.findElement(By.linkText("Books")));
        driver.findElement(By.linkText("Books")).click();

        //Search for a book
        wait.until(driver -> driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]")));
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(authorName);
        
        //Click on enter
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/span/button")).click();;
        
        //Verify if the page content contains the author name
        wait.until(driver -> driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div/div/section[1]/section[1]/div/div[1]/div[1]/h1/span")));
        if(driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div/div/section[1]/section[1]/div/div[1]/div[1]/h1/span")).getText().equals(authorName)){
            assertTrue(true);
        }
        else{
            assertTrue(false);
        }
    }

    @Test
    public void test2() throws InterruptedException{
        System.out.println("Test 2");

        //Hover over the book
        wait.until(driver -> driver.findElement(By.linkText("Audiobooks")));
        action.moveToElement(driver.findElement(By.linkText("Audiobooks"))).perform();
        
        //Click on Audiobooks Top 100
        wait.until(driver -> driver.findElement(By.linkText("Audiobooks Top 100")));
        driver.findElement(By.linkText("Audiobooks Top 100")).click();
        
        //ScrollDown
        js.executeScript("window.scrollBy(0,400)");

        //Click on the first book
        driver.findElement(By.linkText("Funny Story")).click();

        //ScrollDown
        js.executeScript("window.scrollBy(0,400)");

        //Select eBook
        wait.until(driver -> driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/div[1]/section/div/div/div/div/div[3]")));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/div[1]/section/div/div/div/div/div[3]")).click();

        //Add the first book to the cart
        driver.findElement(By.xpath("//*[@id=\'prodInfoContainer\']/div[3]/form[1]/input[10]")).click();
        
        // //Verify if the book is added to the cart
        Thread.sleep(10000);
        assertTrue(driver.findElement(By.xpath("/html/body/div[37]/div/div/div[2]/div[3]/div/div[1]")).getText().equals("Item Successfully Added To Your Cart"));
        // assertTrue(true);
    }

    @Test
    public void test3() throws InterruptedException{
        System.out.println("Test 3");

        //Click on B&N Membership
        wait.until(driver -> driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")));
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/div")).click();

        //Scroll Down
        js.executeScript("window.scrollBy(0,400)");

        //Click on JOIN REWARDS
        wait.until(driver -> driver.findElement(By.linkText("JOIN REWARDS")));


    }

    @AfterTest
    public void tearDown() {
        System.out.println("After Test");
        extentReports.flush();
        driver.quit();
    }
}
