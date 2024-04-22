package com.example.classexercise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClassexerciseApplication {

	public static void main(String[] args) throws InterruptedException{

		WebDriver driver=new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);


		driver.get("https://www.moneycontrol.com/");

		//Maximise the window
		driver.manage().window().maximize();


		// Wait for the search button to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_str")));
		
		// Search for Reliance Industries
		driver.findElement(By.id("search_str")).sendKeys("Reliance Industries");

		actions.moveToElement(driver.findElement(By.id("search_str"))).click();

		// Explicit wait for suggestions to appear
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/div[2]/div[2]/ul/li[1]")));
		
		//Select the first suggestion
		driver.findElement(By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/div[2]/div[2]/ul/li[1]")).click();

		// Scroll down
		js.executeScript("window.scrollBy(0,500)");
		
		// Check if Reliance Industries is present in the page
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Reliance Industries Ltd.")));
		WebElement reliance = driver.findElement(By.linkText("Reliance Industries Ltd."));
		
		if(reliance.isDisplayed()){
				System.out.println("Reliance Industries is present in the page");
		}
		else{
			System.out.println("Reliance Industries is not present in the page");
		}

		//Click on Mutual Funds in the navbar
		driver.findElement(By.linkText("Mutual Funds")).click();
		Thread.sleep(1000);

		//Scroll down
		js.executeScript("window.scrollBy(0,500)");

		//Click on SIP
		driver.findElement(By.xpath("/html/body/section/section[1]/div/div/div[1]/div[5]/div/div/div/div[1]/div/div[3]/div[1]/div[1]/ul/li[2]/a")).click();

		Thread.sleep(1000);

		//Close the driver
		driver.close();

		SpringApplication.run(ClassexerciseApplication.class, args);
	}

}
