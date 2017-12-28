package com.epam.pageobjectmodule;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FindFlightsTest {	

	public WebDriver driver;
	private String START_URL = "http://www.volotea.com/en";
	public FindFlightsPage findFlightsPage;
	public SummaryPage summaryPage;
	
	@BeforeClass 
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(START_URL);
		findFlightsPage=new FindFlightsPage(driver);
	}

	@Test
	public void applicationPage() throws Exception{			
		String attributeValue=findFlightsPage.verifyLandingPage();		
		if (attributeValue.contains("FIND FLIGHTS")) {
			assertTrue(true);
		}	
	}
		
	public void clickFlightsTab() throws Exception{							
		assertTrue(findFlightsPage.clickFlightsTab());			
	}
	
	@Test
	@Parameters({ "originCountry", "originState"})
	public void selectOriginStateUsingCountry(String originCountry,String originState) throws Exception{						
		findFlightsPage.selectOriginState(originCountry,originState);			
	}
	
	@Test
	@Parameters({ "destinationCountry", "destinationState"})
	public void selectDestinationStateUsingCountry(String destinationCountry,String destinationState) throws Exception{						
		findFlightsPage.selectDestinationState(destinationCountry,destinationState);			
	}
	
	@Test
	@Parameters({ "travelStartDate"})
	public void verifyTravelStartDate(String travelStartDate) throws Exception{							
		findFlightsPage.selectStartDate(travelStartDate);			
	}		
	
	@Test
	@Parameters({"travelReturnDate"})
	public void verifyTravelReturnDate(String travelReturnDate) throws Exception{							
		findFlightsPage.selectReturnDate(travelReturnDate);			
	}	
	
	public void verifySelectedOriginState() throws Exception{							
		findFlightsPage.getSelectedOriginStateText();			
	}
	
	public void verifySelectedDestinationState() throws Exception{							
		findFlightsPage.getSelectedOriginStateText();			
	}	
	
	@Test
	public void selectNumberOfAdultAndChildPassengers() throws Exception{							
		findFlightsPage.selectNumberOfAdultAndChildPassengers();			
	}
	
	@Test
	public void clickFindFlightsBtn() throws Exception{							
		summaryPage=findFlightsPage.clickFindFlightsButton();			
	}
	
	@Test(dependsOnMethods="clickFindFlightsBtn")
	public void summaryPage() throws Exception{							
		summaryPage.verifySummaryPageIsOpened();			
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}
}

