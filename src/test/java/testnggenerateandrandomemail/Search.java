package testnggenerateandrandomemail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search {
	
WebDriver  driver;
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
	}
	
	@Test(priority=1)
	public void verifySearchForExistingProduct()
	{
		WebElement searchBox = driver.findElement(By.name("search"));
		 searchBox.sendKeys("hp");
		 
		 WebElement searchClick = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		 searchClick.click();
		 
		 Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	@Test(priority=2)
	public void verifySearchForNonExistingProduct()
	{
		WebElement searchBox = driver.findElement(By.name("search"));
		 searchBox.sendKeys("honda");
		 
		 WebElement searchClick = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		 searchClick.click();
		 
		 
		 WebElement productText=driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p"));
		 String actualText=productText.getText();
		 
		 
		 String expected="There is no product that matches the search criteria.";
		
		 
		 Assert.assertEquals(actualText, expected);
	}
	
	@Test(priority=3)
	public void verifySearchWithOutProduct()
	{
		
		 
		 WebElement searchClick = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		 searchClick.click();
		 
		 
		 WebElement productText=driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p"));
		 String actualText=productText.getText();
		 
		 
		 String expected="There is no product that matches the search criteria.";
		
		 
		 Assert.assertEquals(actualText, expected);
	}
}
