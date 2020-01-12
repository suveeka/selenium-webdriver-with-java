package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
	
	@Parameters({"username","password","expectedmessage"})
	@Test(priority = 1,groups = {"negativeTest","smokeTest"})
	public void incorrectUserNameTest(String username, String password, String expectedMessage)
	{
		
		System.out.println("Entered in incorrectUserNameTest with "+username+" and password : "+password
				+" and expected message will be "+expectedMessage );
		//create a Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//maximize browser window
		driver.manage().window().maximize();
		
		//open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		System.out.println("Page is opened to check incorrect username");
		
		//Enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys("tomIdiot");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys("SuperSecretPassword!");
		
		//click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		//Verification
		WebElement flashError = driver.findElement(By.id("flash"));
		//As receiving from outside - String expectedMessageLocal = "Your username is invalid!";
		String actualErrorMessage = flashError.getText();
		
		
		Assert.assertTrue(actualErrorMessage.contains(expectedMessage), "Actual message does not contain expected error message"+"\n"
				+ "Expected Message : "+expectedMessage+"\n"+"Actual Message : "+actualErrorMessage);
		//close browser
		driver.close();
		
		
	}
     		
	//@Test(enabled = false)
	@Parameters({"username","password","expectedmessage"})
	@Test(priority = 2, groups = {"negativeTest"})
	public void incorrectPasswordTest(String username, String password, String expectedMessage)
	{
		System.out.println("Entered in incorrectPasswordTest with "+username+" and password : "+password
				+" and expected message will be "+expectedMessage );
		
		//create a Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//maximize browser window
		driver.manage().window().maximize();
		
		//open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		System.out.println("Page is opened to check incorrect password");
		
		//Enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys("tomsmith");
		
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys("SuperSecretPassword");
		
		//click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		//Verification
		WebElement flashError = driver.findElement(By.id("flash"));
		//As receiving from outside - String expectedMessageLocal = "Your password is invalid!";
		String actualErrorMessage = flashError.getText();
		System.out.println("Expected Message : "+expectedMessage+"\n"+"Actual Message : "+actualErrorMessage);
		
		Assert.assertTrue(actualErrorMessage.contains(expectedMessage), "Actual message does not contain expected error message"+"\n"
				+ "Expected Message : "+expectedMessage+"\n"+"Actual Message : "+actualErrorMessage);
		//close browser
		driver.close();
		
		
	}

}
