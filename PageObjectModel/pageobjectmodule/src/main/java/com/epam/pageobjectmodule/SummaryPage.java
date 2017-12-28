package com.epam.pageobjectmodule;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SummaryPage extends BasePage {

	public SummaryPage(WebDriver driver) {
		super(driver);
	}

	public boolean verifySummaryPageIsOpened() throws Exception {
		driver.findElement(By.xpath("//section[@class='booking-sumary']/h1")).getText();
		return true;
	}

	public String getNoOfFromPassengers() throws Exception {
		String noOfPassengers = driver.findElement(By.xpath("//div[@class='resume-wrapper']/p")).getText();
		return noOfPassengers;
	}

	public String getDepatureDate() throws Exception {
		String depatureDate = driver.findElement(By.xpath("//div[@class='departure']/p/strong[@class='date']"))
				.getText();
		return depatureDate;
	}

	public String getDepatureCity() throws Exception {
		String depatureCity = driver.findElement(By.xpath("//div[@class='departure']/p/strong[2]")).getText();
		return depatureCity;
	}

	public String getReturnDate() throws Exception {
		String returnDate = driver.findElement(By.xpath("//div[@class='return']/p/strong[@class='date']")).getText();
		return returnDate;
	}

	public String getReturnCity() throws Exception {
		String returnCity = driver.findElement(By.xpath("//div[@class='return']/p/strong[2]")).getText();
		return returnCity;
	}

	public PassengerInformationPage clickNextStepButton() {
		driver.findElement(By.xpath("//div[@id='formBookingStep01']/a[contains(text(),'NEXT STEP')]")).click();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		return new PassengerInformationPage(driver);

	}

}
