package com.epam.pageobjectmodule;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FindFlightsPage extends BasePage {

	public int adultPassengersCount = 0;
	public int childPassengersCount = 0;

	public FindFlightsPage(WebDriver driver) {
		super(driver);
	}

	public String verifyLandingPage() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String textName = driver.findElement(By.xpath("//a[@class='button brand md block']")).getText();			
		return textName;
	}
	
	public boolean clickFlightsTab(){		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='navBar']//h2[@itemprop='name'][contains(text(),'FLIGHTS')]")));    
		driver.findElement(By.xpath("//ul[@id='navBar']//h2[@itemprop='name'][contains(text(),'FLIGHTS')]")).click();
		return true;
	}
	
	public void selectOriginState(String originCountry, String originState) throws Exception {
		driver.findElement(By.xpath("//fieldset[@class='origin']")).click();		
		driver.findElement(By.xpath("//h2[@class='title'][contains(text(),'"+originCountry+"')]//following::a[contains(text(),'"+originState+"')]")).click();
		Thread.sleep(3000);
	}

	
	public void selectDestinationState(String destinationCountry,String destinationState) throws Exception {		
		driver.findElement(By.xpath("//fieldset[@class='destination']/input")).click();
		driver.findElement(By.xpath("//h2[@class='title'][contains(text(),'"+destinationCountry+"')]//following::a[contains(text(),'"+destinationState+"')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
		
	public void selectStartDate(String travelStartDate) throws Exception {				
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//following::a[@class='ui-state-default'][contains(text(),'"+travelStartDate+"')]")));
		driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//following::a[@class='ui-state-default'][contains(text(),'"+travelStartDate+"')]"))
				.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
	
	public void selectReturnDate(String travelReturnDate) throws Exception {		
		driver.findElement(By
				.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//following::a[@class='ui-state-default'][contains(text(),'"+travelReturnDate+"')]"))
				.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public String getSelectedOriginStateText()throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String origin = (String) js.executeScript("return document.getElementsByName('origin')[0].value");		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
		return origin;
	}
	
	public String getSelectedDestinationStateText()	throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String destination = (String) js.executeScript("return document.getElementsByName('destination')[0].value");
		return destination;
	}

	public void selectNumberOfAdultAndChildPassengers() throws Exception {
		driver.findElement(By.xpath("//select[@name='adults']/optgroup/option")).click();
		Select selectAdults = new Select(driver.findElement(By.xpath("//select[@name='adults']")));
		selectAdults.selectByValue("number:2");	
		driver.findElement(By.xpath("//select[@name='children']/optgroup/option")).click();
		Select selectChild = new Select(driver.findElement(By.xpath("//select[@name='children']")));
		selectChild.selectByValue("number:2");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public SummaryPage clickFindFlightsButton() throws Exception {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(
				By.xpath("//form[@name='vm.searchSubmit']/a[@role='button'][contains(text(),'FIND FLIGHTS')]"));
		action.moveToElement(element).click(element).build().perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return new SummaryPage(driver);
	}
}
