package com.example;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    Logger log;
    Actions actions;
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        log = LogManager.getLogger(getClass());
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void openMoneyControl() {
        driver.get("https://www.moneycontrol.com");
        log.info("Opened Website");
    }

    @Test(dependsOnMethods = "openMoneyControl")
    public void hoverMutualFunds() {
        // hover the mutual funds
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Mutual Funds")));
        WebElement mutualFund = driver.findElement(By.linkText("Mutual Funds"));
        actions.moveToElement(mutualFund);

        log.info("mutual funds hovered without clicking");
    }

    @Test(dependsOnMethods = "hoverMutualFunds")
    public void navigateToSIPReturns() {
        // after hovering on mutual funds, goto sip returns
        WebElement mutualFund = driver.findElement(By.linkText("Mutual Funds"));
        actions.moveToElement(mutualFund).perform();
        WebElement sipReturns = driver.findElement(By.cssSelector(
                "#common_header > div.header_desktop > div.bottom_nav > nav > div > ul > li:nth-child(10) > div > div > ul > li:nth-child(2) > ul > li:nth-child(5) > a"));
        sipReturns.click();
        log.info("navigated to SIP Returns");
    }

    @Test(dependsOnMethods = "navigateToSIPReturns")
    public void selectAxisFund() {
        // select Axis bank on mutual fund
        By elementXpath = By.xpath("//*[@id=\"ff_id\"]");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));

        WebElement mutual = driver.findElement(elementXpath);
        Select select = new Select(mutual);
        select.selectByValue("AA");

        log.info("selected Aixs Bank");
    }

    @Test(dependsOnMethods = "selectAxisFund")
    public void selectScheme() {
        // select given scheme
        By elementXpath = By.xpath("//*[@id=\"im_id\"]");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));

        WebElement scheme = driver.findElement(elementXpath);
        Select select = new Select(scheme);
        select.selectByValue("MAA587");

        log.info("selected given scheme");
    }

    @Test(dependsOnMethods = "selectScheme")
    public void setAmount() {
        // enter the amount as 100000
        By elementXpath = By.xpath("//*[@id=\"invamt\"]");
        String amountToBeFilled = "100000";
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));

        WebElement amount = driver.findElement(elementXpath);
        amount.sendKeys(amountToBeFilled);

        log.info("amount setted as 100000");
    }

    @Test(dependsOnMethods = "setAmount")
    public void setStartDate() {
        // set the start date
        String start_date = "2021-08-02";
        By elementXpath = By.xpath("//*[@id=\"stdt\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));

        WebElement startDate = driver.findElement(elementXpath);
        startDate.sendKeys(start_date);

        log.info("start date setted as 2021-08-02");
    }

    @Test(dependsOnMethods = "setStartDate")
    public void setEndDate() {
        // set the end date
        String end_date = "2023-08-17";
        By elementXpath = By.xpath("//*[@id=\"endt\"]");
        wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));

        WebElement startDate = driver.findElement(elementXpath);
        startDate.sendKeys(end_date);

        log.info("end date setted as 2023-08-17");
    }

    @Test(dependsOnMethods = "setEndDate")
    public void calculateMutualFund() {
        // caluclate mutual funds
        By elementXpath = By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input");

        WebElement calculateButton = driver.findElement(elementXpath);
        calculateButton.click();

        log.info("calculating Mutual Funds");
    }

    @Test(dependsOnMethods = "calculateMutualFund")
    public void getInvestmentPeriod() {
        // get the investment period
        By elementXpath = By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(elementXpath));
        WebElement investment = driver.findElement(elementXpath);

        log.info(investment.getText());
    }

    @Test(dependsOnMethods = "getInvestmentPeriod")
    public void getInvestedAmount() {
        // get total invvested amount
        By elementXpath = By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[3]");
        WebElement amount = driver.findElement(elementXpath);

        log.info(amount.getText());
    }

    @AfterTest
    public void close() {
        // close the driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.quit();
    }
}