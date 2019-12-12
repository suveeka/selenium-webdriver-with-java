package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTest {

	@Test
	public void loginTests() {
		
		System.out.println("test is started");
		
		//Create Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		//maximize browser window
		driver.manage().window().maximize();
		
		
		//open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		
		
		//enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		
		//enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		
		
		//click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		//verification 
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page is not the same as expected" );
	
		
		//logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "logout button is not displayed");
		
		//successful login message
		WebElement successmessage = driver.findElement(By.cssSelector("#flash"));
		
		//close browser
		driver.quit();
	}
}
