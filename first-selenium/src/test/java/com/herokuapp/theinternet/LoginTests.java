package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
	
	@Parameters({"username","password","expectedMessage"})
	@Test(priority = 1)
	public void positiveTests(String username, String password, String expectedMessage) {
		
		//install chromeDriver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//maximize the window
		driver.manage().window().maximize();
		
		//Open web page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		//Enter Username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		
		//Enter Password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);
		
		//Click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		//Verify entered page is the desired one
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl, "Actual page is not same as the expected one");
		
		//Logout button is displayed
		WebElement logoutButton = driver.findElement(By.xpath("//button[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not displayed");
		
		//Successful login message
		WebElement successMessage = driver.findElement(By.id("#flash"));
		Assert.assertTrue(successMessage.isDisplayed(), "Login successful message is not displayed");
		
		//close the driver
		driver.close();
	}

	@Parameters({"username","password","expectedMessage"})
	@Test(priority = 2)
	public void negativeTests(String username, String password, String expectedMessage)
	{
		//install driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//get url
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		//enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		
		//enter password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);
		
		//click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
				
		//verify credentials
		WebElement flashError = driver.findElement(By.id("#flash"));
		String actualFlashMessage = flashError.getText();
		
		Assert.assertTrue(actualFlashMessage.contains(expectedMessage), "Actual message does not contain expected error message"+"\n"
				+ "Expected Message : "+expectedMessage+"\n"+"Actual Message : "+actualFlashMessage);
		
		//close the driver
		driver.close();
	}
}
