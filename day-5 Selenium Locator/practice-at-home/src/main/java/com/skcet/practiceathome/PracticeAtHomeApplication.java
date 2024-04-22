package com.skcet.practiceathome;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeAtHomeApplication {
public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		driver = new ChromeDriver();
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		driver.get("https://magento.softwaretestingboard.com/");

		//click the search
		driver.findElement(By.id("search")).click();
		Thread.sleep(3000);

		//enter the Shoes in search
		driver.findElement(By.id("search")).sendKeys("Shoes");
		Thread.sleep(3000);

		//click the search icon to search
		driver.findElement(By.xpath("//*[@id='search_mini_form']/div[2]/button")).click();
		Thread.sleep(3000);

		//check the presence
		if(driver.findElement(By.xpath("//main[@id='maincontent']/div/h1/span")).getText().contains("Shoes"))
		{
			System.out.println("Confirmed the Presence of Text: Shoes");
		}
		else{
			System.out.println("Text: Shoes was not present");
		}

		//Capture Screen using TakeScreenshot
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./screenshots/shoe-evidence.png"));
		Thread.sleep(3000);

		//hover to Men
		actions 
			.moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-5\"]/span[2]")))
			.perform();
		Thread.sleep(1000);

		//hover to Tops
		actions
			.moveToElement(driver.findElement(By.xpath("//*[@id=\"ui-id-17\"]/span[2]")))
			.perform();
		Thread.sleep(1000);

		//click hoodies & sweatshirts
		driver.findElement(By.xpath("//*[@id=\"ui-id-20\"]/span")).click();
		Thread.sleep(4000);

		//navigate back to home
		driver.navigate().back();
		driver.navigate().back();

		// driver.navigate().to("https://magento.softwaretestingboard.com/");

		//scroll down to shop tees
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(2000);

		// go to shop tees
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[1]/div/a[2]/span[2]/span[2]")).click();
		Thread.sleep(3000);

		//scroll down to view
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(2000);

		//click the third product
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[4]/ol/li[3]/div/a/span/span/img")).click();
		Thread.sleep(2000);
		
		// scroll to add to cart
		actions
				.scrollToElement(driver.findElement(By.id("product-addtocart-button")))
				.perform();
		Thread.sleep(3000);

		//set the size M
		driver.findElement(By.id("option-label-size-143-item-168")).click();
		Thread.sleep(3000);

		//set the color orange
		driver.findElement(By.id("option-label-color-93-item-56")).click();
		Thread.sleep(3000);

		//clear the quantity
		driver.findElement(By.id("qty")).clear();

		//update quantity to 4
		driver.findElement(By.id("qty")).sendKeys("4");
		Thread.sleep(1000);

		//add to cart
		driver.findElement(By.id("product-addtocart-button")).click();
		Thread.sleep(5000);

		driver.quit();
	}
}
