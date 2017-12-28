package com.epam.pageobjectmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassengerInformationPage extends BasePage {

	public int adultPassengersCount;

	public PassengerInformationPage(WebDriver driver) {
		super(driver);
	}

	public Boolean verifyPassengerInformationPageIsOpened() throws Exception {
		driver.findElement(By.xpath("//h2[@class='title main icon passenger']")).getText();
		return true;
	}

	public boolean enterAdultFirstNameAndLastName(String adultPassengerFirstName, String adultPassengerSecondName) {
		adultPassengersCount = driver
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
		return true;
	}

	public boolean enterChildFirstNameAndLastName(String childPassengerFirstName, String childPassengerSecondName) {
		int childPassengersCount = driver
				.findElements(By
						.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]"))
				.size();
		for (int childPassengerCnt = adultPassengersCount; childPassengerCnt < adultPassengersCount
				+ childPassengersCount; childPassengerCnt++) {
			driver.findElement(By
					.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
							+childPassengerCnt+"')]"))
					.sendKeys(childPassengerFirstName);
			driver.findElement(By
					.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'Surname')][contains(@id,'surname1_"
							+childPassengerCnt+"')]"))
					.sendKeys(childPassengerSecondName);
		}
		return true;
	}
}
