package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public SearchTest() {
		super();
	}

	public WebDriver driver;
	SearchPage searchPage;
//	HomePage homePage = new HomePage(driver);

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterMethod
	public void teardown() {
//		driver.close();
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchwithValidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductInToSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickSearchButton();
//		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(), "HP Product is not displayed");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductInToSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickSearchButton();
		Assert.assertEquals(searchPage.noProductMessage(), dataProp.getProperty("noProductTextInSearchResults"), "No product Message is not displayed");
//		Assert.assertEquals(searchPage.noProductMessage(), dataProp.getProperty("noProductTextInSearchResults"),
//				"No product Message is not displayed");
	}

	@Test(priority = 3, dependsOnMethods = { "verifySearchWithInvalidProduct" })
	public void verifySearchWithoutAnyProduct() {

		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickSearchButton();
		Assert.assertEquals(searchPage.noProductMessage(), dataProp.getProperty("noProductTextInSearchResults"),
				"No product Message is not displayed");
	}
}
