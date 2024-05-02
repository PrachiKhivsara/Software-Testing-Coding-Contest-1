package com.example;


import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
    JavascriptExecutor js;
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");

        driver = new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().window().maximize();

        js = (JavascriptExecutor) driver;

    }
    @Test
    public void shouldAnswerWithTrue()
    {
        driver.findElement(By.id("username")).sendKeys("S@gmail.com");
        driver.findElement(By.id("password")).sendKeys("P@ssword12");
        driver.findElement(By.id("submit")).click();

        if(driver.getCurrentUrl().equals("http://dbankdemo.com/bank/home")) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
        assertTrue(driver.getCurrentUrl().equals("http://dbankdemo.com/bank/ome"));
    }

    @Test
    public void test2() {
        System.out.println("Test 2");

        //Click on the Deposit button
        driver.findElement(By.id("deposit-menu-item")).click();

        //Select the dropdown list
        Select select=new Select(driver.findElement(By.id("selectedAccount")));

        //Select the option with value 434969
        select.selectByValue("434969");

        //Enter the amount
        driver.findElement(By.id("amount")).sendKeys("1000");

        //Click on submit
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
    }

    @Test
    public void test3() {
        System.out.println("Test 3");

        //Click on the Deposit button
        driver.findElement(By.id("withdraw-menu-item")).click();

        //Select the dropdown list
        Select select=new Select(driver.findElement(By.id("selectedAccount")));


        //Select the option with value 434969
        select.selectByValue("434969");

        //Enter the amount
        driver.findElement(By.id("amount")).sendKeys("1000");

        //Click on submit
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
        driver.quit();
    }
}
