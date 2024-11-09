package testngpropertyfile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
	
	WebDriver  driver;
	Properties prop;
	
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@BeforeMethod
	public void setup()
	{  
		
		try
		{
		 prop=new Properties();
		 File propFile=new File(System.getProperty("user.dir")+"\\src\\test\\java\\testngpropertyfile\\data.properties");
		 FileReader fr=new FileReader(propFile);
		 prop.load(fr);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		
		WebElement myAccount = driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccount.click();
		
		WebElement login = driver.findElement(By.linkText("Login"));
		login.click();
	}
	
	@Test(priority=1)
	public void loginWithValidCredentials()
	{
		WebElement emailEnter = driver.findElement(By.id("input-email"));
		emailEnter.sendKeys(prop.getProperty("email"));
		
		WebElement passwordEnter = driver.findElement(By.id("input-password"));
		passwordEnter.sendKeys(prop.getProperty("epassword"));
		
		WebElement loginclick = driver.findElement(By.xpath("//input[@value='Login']"));
		loginclick .click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
	}


@Test(priority=2)
public void loginWithInValidCredentials()
{
	WebElement emailEnter = driver.findElement(By.id("input-email"));
	emailEnter.sendKeys(generateEmailWithTimeStamp());
	
	WebElement passwordEnter = driver.findElement(By.id("input-password"));
	passwordEnter.sendKeys(prop.getProperty("invalidpassword"));
	
	WebElement loginclick = driver.findElement(By.xpath("//input[@value='Login']"));
	loginclick .click();
	
	String expectedText="Warning: No match for E-Mail Address and/or Password.";
	
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedText));
	
}
@Test(priority=3)
public void loginWithInValidEmailValidPassword()
{
	WebElement emailEnter = driver.findElement(By.id("input-email"));
	emailEnter.sendKeys(generateEmailWithTimeStamp());
	
	WebElement passwordEnter = driver.findElement(By.id("input-password"));
	passwordEnter.sendKeys(prop.getProperty("epassword"));
	
	WebElement loginclick = driver.findElement(By.xpath("//input[@value='Login']"));
	loginclick .click();
	
	String expectedText="Warning: No match for E-Mail Address and/or Password.";
	
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedText));
	
}
@Test(priority=3)
public void loginWithValidEmailInValidPassword()
{
	WebElement emailEnter = driver.findElement(By.id("input-email"));
	emailEnter.sendKeys(randomEmail());
	
	WebElement passwordEnter = driver.findElement(By.id("input-password"));
	passwordEnter.sendKeys(prop.getProperty("invalidpassword"));
	
	WebElement loginclick = driver.findElement(By.xpath("//input[@value='Login']"));
	loginclick .click();
	
	String expectedText="Warning: No match for E-Mail Address and/or Password.";
	
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedText));
	
}

@Test(priority=5)
public void loginWithOutAnyCredentials()
{
	
	
	WebElement loginclick = driver.findElement(By.xpath("//input[@value='Login']"));
	loginclick .click();
	
	String expectedText="Warning: No match for E-Mail Address and/or Password.";
	
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedText));
	
}
public String randomEmail()
{
	String[] validEmail= {"amotooricap1@gmail.com","amotooricap2@gmail.com","amotooricap3@gmail.com",
			"amotooricap4@gmail.com","amotooricap5@gmail.com","amotooricap6@gmail.com",
			         "amotooricap7@gmail.com","amotooricap8@gmail.com"};
	

  
	 return validEmail[new Random().nextInt(8)];
}

public String generateEmailWithTimeStamp()
{
	return "shimna"+new Date().toString().replace(" ", "_").replace(":", "_")+"@gmail.com";
}

}
