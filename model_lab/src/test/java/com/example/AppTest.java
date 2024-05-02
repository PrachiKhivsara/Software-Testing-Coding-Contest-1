package com.example;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    String URL="https://www.shoppersstop.com/";
    @BeforeTest
    public void BeforeTest(){
        driver=new ChromeDriver();

        driver.get(URL);

        driver.manage().window().maximize();

    }

    @Test
    public void shouldAnswerWithTrue()
    {
        //Get Title of driver
        System.out.println(driver.getTitle());

        //Click on profile
        driver.findElement(By.className("user-icon")).click();

        //Get length of pagesource
        System.out.println(driver.getPageSource().length());

        //Navigate to google.com
        driver.navigate().to("https://www.google.com");

        //Navigate back to previous page
        driver.navigate().back();

        //Check if current url equals shoppers stop url
        if(driver.getCurrentUrl().equals(URL)){
            System.out.println("In Shoppers Stop");
        }
        else{
            System.out.println("Not in Shoppers Stop");
        }

        assertTrue( true );
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
