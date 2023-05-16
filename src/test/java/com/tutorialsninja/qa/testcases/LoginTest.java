package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public LoginTest() { // constructor
		super(); // super class -> will act as child for parent class
	}

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod // TestNG Annotations
	public void setup() throws InterruptedException {

		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectloginOption();
	}

	@AfterMethod // TestNG Annotations
	public void teardown() {
//		driver.close();
		driver.quit();
	}

	@DataProvider(name = "validCredentialsSupplier") // TestNG Annotations with attribute
	public Object[][] supplyTestDataFromSpreadsheet() {

//		Object [][] data = {{"amotoori3@gmail.com","12345"},{"amotoori1@gmail.com","12345"},{"amotoori1@gmail.com","12345"}};
		Object[][] data = Utilities.getDataFromExcel("Sheet1");
		return data;
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier") // TestNG Annotations with attribute
	public void verifyLoginWithValidCredentials(String email, String password) {

		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());

//		driver.findElement(By.name("email")).sendKeys(prop.getProperty("validEmail"));   	   --> From properties file
//		driver.findElement(By.name("email")).sendKeys(email);                                  // -> from dataprovider 
////		driver.findElement(By.name("password")).sendKeys(prop.getProperty("validPassword"));   --> From properties file
//		driver.findElement(By.name("password")).sendKeys(password);                            // -> from dataprovider
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));

		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessagetext();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidCredentialsAndValidPassword() {

		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));

		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessagetext();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword(String email) {

		accountPage = loginPage.login(email, dataProp.getProperty("invalidPassword"));

		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessagetext();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		loginPage.clickOnLoginButton();

		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessagetext();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatching");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message is not displayed");

	}

//	public String generateEmailWithTimeStamp() {
//		Date date = new Date();
//		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
//		return "amotoori"+timeStamp+"@gmail.com";
//	}
}
