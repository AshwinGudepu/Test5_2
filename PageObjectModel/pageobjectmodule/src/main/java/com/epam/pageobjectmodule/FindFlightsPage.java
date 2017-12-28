package com.epam.pageobjectmodule;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

public class FindFlightsPage extends BasePage {
	
	public int adultPassengersCount = 0;
	public int childPassengersCount = 0;
	
	@FindBy(xpath = "//a[@class='button brand md block']")
    private WebElement getTitleOfHomePage;

	@FindBy(xpath = "//ul[@id='navBar']//h2[@itemprop='name'][contains(text(),'FLIGHTS')]")
	private By clickFlightsButton;
	
	@FindBy(xpath = "//fieldset[@class='origin']")
	private WebElement clickOriginInputBox;		
		
	@FindBy(xpath = "//fieldset[@class='destination']/input")
	private WebElement clickDestinationInputBox;
				
	public FindFlightsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String verifyLandingPage() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String textName = getTitleOfHomePage.getText();			
		return textName;
	}
	
	public boolean clickFlightsTab(){		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickFlightsButton));    
		((WebElement) clickFlightsButton).click();
		return true;
	}
	
	public void selectOriginState(String originCountry, String originState) throws Exception {
		driver.findElement(By.xpath("//fieldset[@class='origin']")).click();		
		driver.findElement(By.xpath("//h2[@class='title'][contains(text(),'"+originCountry+"')]//following::a[contains(text(),'"+originState+"')]")).click();
		Thread.sleep(3000);
	}
	
	public WebElement waitForElement(final String locator) {
		WebElement element = null;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(locator));

			}
		});
		return element;
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

	@FindBy(xpath = "//select[@name='adults']/optgroup/option")
	private WebElement adultDropDown;

	@FindBy(xpath = "//select[@name='adults']")
	private WebElement selectingAdultNumber;
	
	@FindBy(xpath = "//select[@name='children']/optgroup/option")
	private WebElement childDropDown;

	@FindBy(xpath = "//select[@name='children']")
	private WebElement selectingChildNumber;

	public void selectNumberOfAdultAndChildPassengers() throws Exception {
		adultDropDown.click();
		Select selectAdults = new Select(selectingAdultNumber);
		selectAdults.selectByValue("number:2");	
		childDropDown.click();
		Select selectChild = new Select(selectingChildNumber);
		selectChild.selectByValue("number:2");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@FindBy(xpath = "//form[@name='vm.searchSubmit']/a[@role='button'][contains(text(),'FIND FLIGHTS')]")
	private WebElement clickFindFLightsButton;
	
	public SummaryPage clickFindFlightsButton() throws Exception {
		Actions action = new Actions(driver);
		WebElement element = clickFindFLightsButton;
		action.moveToElement(element).click(element).build().perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return new SummaryPage(driver);
	}
}
