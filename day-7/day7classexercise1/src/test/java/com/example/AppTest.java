package com.example;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

class AppTest {

    WebDriver driver;
    Actions actions;
    Wait<WebDriver> wait;

    @BeforeTest
    public void setup() {
        this.driver = new ChromeDriver();
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testEconomicTimes() {
        // Step 1
        driver.get("https://economictimes.indiatimes.com/et-now/results");

        // Step 2
        WebElement link = wait.until(d -> d.findElement(By.linkText("Mutual Funds")));
        link.click();

        // Step 3
        WebElement amc = wait.until(d -> d.findElement(By.id("amcSelection")));
        actions.scrollToElement(amc).perform();

        // Step 4
        amc.click();
        Select amcSelect = new Select(amc);
        amcSelect.selectByVisibleText("Canara Robeco");

        // Step 5
        WebElement scheme = wait.until(d -> d.findElement(By.id("schemenm")));
        Select schemeSelect = new Select(scheme);
        schemeSelect.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");

        // Step 6
        WebElement getDetailsLink = driver.findElement(By.id("getDetails"));
        getDetailsLink.click();

        // Step 7
        Set<String> handles = driver.getWindowHandles();

        // Switch to the new window
        for (String handle : handles) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
            }
        }

        // Step 8
        WebElement investmentType = driver.findElement(By.id("installment_type"));
        investmentType.click();
        driver.findElement(By.cssSelector("[data-value=\"sip\"]")).click();

        WebElement investmentAmount = driver.findElement(By.id("installment_amt"));
        investmentAmount.click();
        driver.findElement(By.cssSelector("[data-value=\"1000\"]")).click();

        WebElement investmentPeriod = driver.findElement(By.id("installment_period"));
        investmentPeriod.click();
        driver.findElement(By.cssSelector("[data-value=\"1095\"]")).click();

        // Step 9
        WebElement returns = driver.findElement(By.cssSelector("[data-offset=\"mfReturnsOffset\"]"));
        returns.click();

        // Step 10
        WebElement annualizedReturnsRow = driver.findElement(By.cssSelector("#mfReturns table tbody tr"));
        System.out.println(annualizedReturnsRow.getText());
    }

    @AfterTest
    public void wrapUp() {
        driver.quit();
    }

}