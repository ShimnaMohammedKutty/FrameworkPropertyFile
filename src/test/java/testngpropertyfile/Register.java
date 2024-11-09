package testngpropertyfile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register {
	
	WebDriver  driver;
	Properties prop=null;
	
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
		
		WebElement register = driver.findElement(By.linkText("Register"));
		register.click();
	}
	
	@Test(priority=1)
	public void registerWithMandatoryyField()
	{
		
		
		WebElement firstName = driver.findElement(By.id("input-firstname"));
		firstName.sendKeys(prop.getProperty("firstname"));
		
		
		WebElement lastName = driver.findElement(By.id("input-lastname"));
		lastName.sendKeys(prop.getProperty("lastname"));
		
		
		WebElement emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(generateEmailWithTimeStamp());
		
		WebElement phone = driver.findElement(By.id("input-telephone"));
		phone.sendKeys(prop.getProperty("telephone"));
		
		WebElement passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(prop.getProperty("password"));
		
		WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
		confirmPassword.sendKeys(prop.getProperty("password"));
		
		WebElement privacyPolicy = driver.findElement(By.name("agree"));
		privacyPolicy.click();
		
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		continueButton.click();
		
		String expectedTitle = "Your Account Has Been Created!";
		
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	
	@Test(priority=2)
	public void registerWithAllFields()
	{
		
		WebElement firstName = driver.findElement(By.id("input-firstname"));
		firstName.sendKeys(prop.getProperty("firstname"));
		
		
		WebElement lastName = driver.findElement(By.id("input-lastname"));
		lastName.sendKeys(prop.getProperty("lastname"));
		
		
		WebElement emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(generateEmailWithTimeStamp());
		
		WebElement phone = driver.findElement(By.id("input-telephone"));
		phone.sendKeys(prop.getProperty("telephone"));
		
		WebElement passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(prop.getProperty("password"));
		
		WebElement confirmPassword = driver.findElement(By.id("input-confirm"));
		confirmPassword.sendKeys(prop.getProperty("password"));
		
		WebElement newsLetter = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		newsLetter.click();
		
		WebElement privacyPolicy = driver.findElement(By.name("agree"));
		privacyPolicy.click();
		
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		continueButton.click();
		
		String expectedTitle = "Your Account Has Been Created!";
		
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Test(priority=3)
	public void registerWithoutFieldsDisplayMessages()
	{
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		continueButton.click();
		
		
		String firstNameMessage="First Name must be between 1 and 32 characters!";
		String lastNameMessage="Last Name must be between 1 and 32 characters!";
		String emailMessage="E-Mail Address does not appear to be valid!";
		String phoneMessage="Telephone must be between 3 and 32 characters!";
		String passwordMessagge="Password must be between 4 and 20 characters!";
		String privacyPolicyMessage="Warning: You must agree to the Privacy Policy!";
		
	
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-firstname']/following-sibling::div")).getText(),firstNameMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-lastname']/following-sibling::div")).getText(),lastNameMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-email']/following-sibling::div")).getText(),emailMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-telephone']/following-sibling::div")).getText(),phoneMessage);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-password']/following-sibling::div")).getText(),passwordMessagge);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(privacyPolicyMessage));
		
		
		
		
	}
	
	public String generateEmailWithTimeStamp()
	{
		return "shimna"+new Date().toString().replace(" ", "_").replace(":", "_")+"@gmail.com";
	}


}
