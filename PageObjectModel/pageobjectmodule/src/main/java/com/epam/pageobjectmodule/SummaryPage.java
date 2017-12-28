package com.epam.pageobjectmodule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SummaryPage extends BasePage {
	
	public SummaryPage(WebDriver driver) {
		super(driver);		
	}

	@Test
	public void verifySummaryPageIsOpened() throws Exception {
		String summaryPage = driver.findElement(By.xpath("//section[@class='booking-sumary']/h1")).getText();		
	}

	@Test
	public void getDetailsFromSummaryPage() throws Exception {
		String noOfPassengers = driver.findElement(By.xpath("//div[@class='resume-wrapper']/p")).getText();
		//assertTrue(noOfPassengers.contains("4 passengers"));

		String depatureDate = driver.findElement(By.xpath("//div[@class='departure']/p/strong[@class='date']"))
				.getText();
		//assertEquals(depatureDate, "Mon 28 May 2018");
		String depatureCity = driver.findElement(By.xpath("//div[@class='departure']/p/strong[2]")).getText();
		//assertEquals(depatureCity, "Munich · Mykonos");

		String returnDate = driver.findElement(By.xpath("//div[@class='return']/p/strong[@class='date']")).getText();
		//assertEquals(returnDate, "Fri 29 Jun 2018");
		String returnCity = driver.findElement(By.xpath("//div[@class='return']/p/strong[2]")).getText();
		//assertEquals(returnCity, "Mykonos · Munich");

		driver.findElement(By.xpath("//div[@id='formBookingStep01']/a[contains(text(),'NEXT STEP')]")).click();
		//waitForPageToLoad();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}

}
