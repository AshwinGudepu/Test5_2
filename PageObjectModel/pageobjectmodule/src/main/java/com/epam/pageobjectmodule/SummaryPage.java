package com.epam.pageobjectmodule;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SummaryPage extends BasePage {

	public SummaryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);		
	}

	@FindBy(xpath = "//section[@class='booking-sumary']/h1")
	private WebElement summaryPageHeader;			
	public boolean verifySummaryPageIsOpened() throws Exception {
		summaryPageHeader.getText();
		return true;
	}

	@FindBy(xpath = "//div[@class='resume-wrapper']/p")
	private WebElement gettotalNumberOfPassengers;	
	public String getNoOfFromPassengers() throws Exception {
		String noOfPassengers = gettotalNumberOfPassengers.getText();
		return noOfPassengers;
	}

	@FindBy(xpath = "//div[@class='departure']/p/strong[@class='date']")
	private WebElement getDepatureDate;
	public String getDepatureDate() throws Exception {
		String depatureDate = getDepatureDate.getText();
		return depatureDate;
	}

	@FindBy(xpath = "//div[@class='departure']/p/strong[2]")
	private WebElement getDepatureCity;
	
	public String getDepatureCity() throws Exception {
		String depatureCity = getDepatureCity.getText();
		return depatureCity;
	}

	@FindBy(xpath = "//div[@class='return']/p/strong[@class='date']")
	private WebElement getReturnDate;
	
	public String getReturnDate() throws Exception {
		String returnDate = getReturnDate.getText();
		return returnDate;
	}

	@FindBy(xpath = "//div[@class='return']/p/strong[2]")
	private WebElement getReturnCity;
	
	public String getReturnCity() throws Exception {
		String returnCity = getReturnCity.getText();
		return returnCity;
	}

	@FindBy(xpath = "//div[@id='formBookingStep01']/a[contains(text(),'NEXT STEP')]")
	private WebElement clickNextStepButton;
	
	public PassengerInformationPage clickNextStepButton() {
		clickNextStepButton.click();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		return new PassengerInformationPage(driver);

	}

}
