package com.epam.pageobjectmodule;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassengerInformationPage extends BasePage {

	public int adultPassengersCount;

	public PassengerInformationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[@class='title main icon passenger']")
	private WebElement getPassengerInformationPageHeaderText;

	@FindBy(xpath = "//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')]")
	private List<WebElement> getTotalNumbersOfAdults;

	public final int adultPassengerCnt = 0;
	@FindBy(xpath = "//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
			+ adultPassengerCnt + "')]")
	private WebElement enterAdultFirstName;

	@FindBy(xpath = "//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'Surname')][contains(@id,'surname1_"
			+ adultPassengerCnt + "')]")
	private WebElement enterAdultSecondName;

	@FindBy(xpath = "//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]")
	private List<WebElement> getTotalNumbersOfChildren;

	public final int childPassengerCnt = 0;
	@FindBy(xpath = "//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
			+ childPassengerCnt + "')]")
	private WebElement enterChildFirstName;

	@FindBy(xpath = "//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
			+ childPassengerCnt + "')]")
	private WebElement enterChildSecondName;

	public Boolean verifyPassengerInformationPageIsOpened() throws Exception {
		getPassengerInformationPageHeaderText.getText();
		return true;
	}

	public boolean enterAdultFirstNameAndLastName(String adultPassengerFirstName, String adultPassengerSecondName) {
		adultPassengersCount = getTotalNumbersOfAdults.size();
		System.out.println("adultPassengersCount*********************************************"+adultPassengersCount);
		for (int adultPassengerCnt = 0; adultPassengerCnt < adultPassengersCount; adultPassengerCnt++) {
			enterAdultFirstName.sendKeys(adultPassengerFirstName);
			enterAdultSecondName.sendKeys(adultPassengerSecondName);
		}
		return true;
	}

	public boolean enterChildFirstNameAndLastName(String childPassengerFirstName, String childPassengerSecondName) {
		int childPassengersCount = getTotalNumbersOfChildren.size();
		
		for (int childPassengerCnt = adultPassengersCount; childPassengerCnt < adultPassengersCount
				+ childPassengersCount; childPassengerCnt++) {
			System.out.println("adultPassengersCount*********************************************"+childPassengerFirstName);
			enterChildFirstName.sendKeys(childPassengerFirstName);
			System.out.println("adultPassengersCount*********************************************"+childPassengerSecondName);
			enterChildSecondName.sendKeys(childPassengerSecondName);
		}
		return true;
	}
}
