package com.epam.pageobjectmodule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassengerInformationPage extends BasePage {

	public int adultPassengersCount;

	public PassengerInformationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy( xpath = "//h2[@class='title main icon passenger']")
	private WebElement getPassengerInformationPageHeaderText;
	
	public Boolean verifyPassengerInformationPageIsOpened() throws Exception {
		getPassengerInformationPageHeaderText.getText();
		return true;
	}

	@FindBy( xpath = "//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')]")
	private List<WebElement> getTotalNumbersOfAdults;
	
	public boolean enterAdultFirstNameAndLastName(String adultPassengerFirstName, String adultPassengerSecondName) {
		adultPassengersCount = getTotalNumbersOfAdults.size();
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

	@FindBy( xpath = "//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]")
	private List<WebElement> getTotalNumbersOfChildren;
	public boolean enterChildFirstNameAndLastName(String childPassengerFirstName, String childPassengerSecondName) {
		int childPassengersCount =getTotalNumbersOfChildren.size();
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
