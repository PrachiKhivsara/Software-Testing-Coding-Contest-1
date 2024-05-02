package com.example;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Keyword2 {
    WebDriver driver;
     @Test
    public void test1() throws Exception
{
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    FileInputStream fs= new FileInputStream("D:\\input.xlsx");
    XSSFWorkbook workbook=new XSSFWorkbook(fs);
    XSSFSheet sheet= workbook.getSheetAt(2);
    int rowCount=sheet.getLastRowNum();

    for(int i=0;i< rowCount;i++)
    { Row row=sheet.getRow(i+1);
        //Keyword1
        if( row.getCell(0).getStringCellValue().equals("open_url"))
        {
            driver.get("https://www.google.com/");
        }
        //Keyword2
        else if (row.getCell(0).getStringCellValue().equals("get_Title")) {
            System.out.println(driver.getTitle());
        }
    }
    fs.close();
    

}
}