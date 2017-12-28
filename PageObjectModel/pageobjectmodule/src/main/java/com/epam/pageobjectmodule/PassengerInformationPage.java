package com.epam.pageobjectmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PassengerInformationPage extends BasePage {
	
	public int adultPassengersCount;
	
	public PassengerInformationPage(WebDriver driver) {
		super(driver);		
	}

	@Test
	public void verifyPassengerInformationPageIsOpened() throws Exception {
		String passengerDetailsPage = driver.findElement(By.xpath("//h2[@class='title main icon passenger']"))
				.getText();
		//assertEquals(passengerDetailsPage, "PASSENGERS");
		int adultPassengersCount = driver
				.findElements(By
						.xpath("//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')]"))
				.size();
		int childPassengersCount = driver
				.findElements(By
						.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]"))
				.size();
	}
	
	@Test
	@Parameters({ "adultPassengerFirstName", "adultPassengerSecondName" })
	public void enterAdultFirstNameAndLastName(String adultPassengerFirstName, String adultPassengerSecondName) {
		int adultPassengersCount = driver
				.findElements(By
						.xpath("//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')]"))
				.size();
		for (int adultPassengerCnt = 0; adultPassengerCnt < adultPassengersCount; adultPassengerCnt++) {
			driver.findElement(By
					.xpath("//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
							+ adultPassengerCnt + "')]"))
					.sendKeys(adultPassengerFirstName);
			driver.findElement(By
					.xpath("//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'Surname')][contains(@id,'surname1_"
							+ adultPassengerCnt + "')]"))
					.sendKeys(adultPassengerSecondName);
		}
	}

	@Test(dependsOnMethods = { "enterAdultFirstNameAndLastName" })
	@Parameters({ "childPassengerFirstName", "childPassengerSecondName" })
	public void enterChildFirstNameAndLastName(String childPassengerFirstName, String childPassengerSecondName) {
		int childPassengersCount = driver
				.findElements(By
						.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]"))
				.size();
		for (int childPassengerCnt = adultPassengersCount; childPassengerCnt < adultPassengersCount
				+ childPassengersCount; childPassengerCnt++) {
			driver.findElement(By
					.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
							+ childPassengerCnt + "')]"))
					.sendKeys(childPassengerFirstName);
			driver.findElement(By
					.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'Surname')][contains(@id,'surname1_"
							+ childPassengerCnt + "')]"))
					.sendKeys(childPassengerSecondName);
		}
	}

}
