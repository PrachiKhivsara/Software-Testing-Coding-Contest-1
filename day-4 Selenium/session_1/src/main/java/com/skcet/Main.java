package com.skcet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {

        //question 2:
        WebDriver driver1 = new ChromeDriver();
        WebDriver driver2 = new FirefoxDriver();
        WebDriver driver3 = new EdgeDriver();

        driver1.get("https://www.google.com");
        driver2.get("https://www.google.com");
        driver3.get("https://www.google.com");

        driver1.close();
        driver2.close();
        driver3.close();

        //question 3:
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.shoppersstop.com/");
        driver.findElement(By.className("user-icon")).click();
    }
}