package com.epam.pageobjectmodule;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class BasePage {
	protected WebDriver driver;
	public int timeInSeconds = 30;	

	public BasePage(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	public boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}
	
	/*public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}

	}
*/
	public void waitForElementToPresent(By by) {
		try {
			new WebDriverWait(driver, timeInSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isElementDisplayed(String XPath) {
		if (driver.findElements(By.xpath(XPath)).size() > 0) {
			return true;
		}
		return false;
	}
}
