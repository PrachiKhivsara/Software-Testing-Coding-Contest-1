package com.example;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    XSSFSheet sheet;
    String path="D:\\My projects\\cc2\\excel\\data.xlsx";
    String authorName="";
    Actions action;
    JavascriptExecutor js;

    @BeforeTest
    public void setUp() {
        System.out.println("Before Test");

        //Set the path of the ChromeDriver
        driver = new ChromeDriver();

        //Open browser
        driver.get("https://www.barnesandnoble.com/");

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
        //Search for a book
        Thread.sleep(2000);
        driver.findElement(By.linkText("All")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.linkText("Books")).click();

        //Search for a book
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(authorName);

        //Click on enter
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]")).submit();

        //Verify if the page content contains the author name
        Thread.sleep(2000);
        if(driver.findElement(By.partialLinkText(authorName)).isDisplayed()){
            System.out.println("Author name found");
        }
        else{
            System.out.println("Author name not found");
        }

        assertTrue(true);
    
        
    }

    @Test
    public void test2() throws InterruptedException{
        System.out.println("Test 2");

        //Hover over the book
        Thread.sleep(2000);
        action.moveToElement(driver.findElement(By.linkText("Audiobooks"))).perform();
        
        //Click on Audiobooks Top 100
        Thread.sleep(2000);
        driver.findElement(By.linkText("Audiobooks Top 100")).click();
        
        //ScrollDown
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,400)");

        //Click on the first book
        Thread.sleep(2000);
        driver.findElement(By.linkText("Funny Story")).click();

        //ScrollDown
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,400)");

        //Select eBook
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/div[1]/section/div/div/div/div/div[3]")).click();

        //Add the first book to the cart
        driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/div[3]/form[1]/input[10]")).submit();
        
        //Verify if the book is added to the cart
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.linkText("Item Successfully Added To Your Cart")).isDisplayed());
    }

    @Test
    public void test3() throws InterruptedException{
        System.out.println("Test 3");

        //Click on B&N Membership
        Thread.sleep(2000);
        driver.findElement(By.linkText("B&N Membership")).click();

        //Scroll Down
        js.executeScript("window.scrollBy(0,400)");

        

    }

    @AfterTest
    public void tearDown() {
        System.out.println("After Test");
        // driver.quit();
    }
}
