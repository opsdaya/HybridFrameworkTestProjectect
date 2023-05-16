package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountSuccessPage;

	@AfterMethod
	public void teardown() {
//		driver.close();
		driver.quit();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerpage = homePage.selectRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

//		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickPolicyCheckBox();
		accountSuccessPage = registerpage.clickContinueButton();
//		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccesspageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success Page Is Not Displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvingAllFields() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clicknewsLetterOption();
		registerpage.clickPolicyCheckBox();
		accountSuccessPage = registerpage.clickContinueButton();

//		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccesspageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account Success Page Is Not Displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clicknewsLetterOption();
		registerpage.clickPolicyCheckBox();
		registerpage.clickContinueButton();
		String actualWarningMessage = registerpage.retrieveDuplicareEmailAddressWarning();
		String expectedWarningmessage = dataProp.getProperty("duplicateEmailWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningmessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickContinueButton();

		String actualPrivacyPolicyWarningMessage = registerpage.retrievePrivatePolicyWarningMessage();
		String expectedPrivacyPolicyWarningmessage = dataProp.getProperty("privacyPolicyWarning");
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningmessage),
				"Expected Privacy Policy Warning Message Is Not Displayed");

		String actualFirstNameWarningMessage = registerpage.retrieveFirstNameWarningMessage();
		Assert.assertEquals(actualFirstNameWarningMessage, dataProp.getProperty("firstNameWarning"));

		String actualLastNameWarningMessage = registerpage.retrieveLastNameWarningMessage();
		Assert.assertEquals(actualLastNameWarningMessage, dataProp.getProperty("lastNameWarning"));

		String actualEmailWarningMessage = registerpage.retrieveEmailWarningMessage();
		Assert.assertEquals(actualEmailWarningMessage, dataProp.getProperty("emailWarning"));

		String actualtelephoneWarningMessage = registerpage.retrieveTelephoneNumberWarningMessage();
		Assert.assertEquals(actualtelephoneWarningMessage, dataProp.getProperty("telephoneWarning"));

		String actualPasswordWarningMessage = registerpage.retrievePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarningMessage, dataProp.getProperty("passwordWarning"));

	}
}
