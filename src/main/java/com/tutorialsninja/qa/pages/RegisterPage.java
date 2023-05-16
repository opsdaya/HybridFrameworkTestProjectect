package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	// Objects
	@FindBy(name = "firstname")
	private WebElement firstNameField;

	@FindBy(name = "lastname")
	private WebElement lastNameField;

	@FindBy(name = "email")
	private WebElement emailAddressField;

	@FindBy(name = "telephone")
	private WebElement telephoneField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(name = "confirm")
	private WebElement confirmPasswordField;

	@FindBy(name = "agree")
	private WebElement PolicyField;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement continueButton;

	@FindBy(xpath = "(//label[@class='radio-inline']/input[@type='radio'])[1]")
	private WebElement newsLetter;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarningMessage;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;

	@FindBy(xpath = "//input[@name='firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath = "//input[@name='lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath = "//input[@name='email']/following-sibling::div")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath = "//input[@name='telephone']/following-sibling::div")
	private WebElement telephoneNumberWarningMessage;
	
	@FindBy(xpath = "//input[@name='password']/following-sibling::div")
	private WebElement passwordWarningMessage;
	
	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// actions(methods)
	public void enterFirstName(String firstNameText) {

		firstNameField.sendKeys(firstNameText);

	}

	public void enterLastName(String lastNameText) {

		lastNameField.sendKeys(lastNameText);

	}

	public void enterEmail(String emailText) {

		emailAddressField.sendKeys(emailText);

	}

	public void enterTelephoneNumber(String telephoneText) {

		telephoneField.sendKeys(telephoneText);

	}

	public void enterPassword(String passwordText) {

		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {

		confirmPasswordField.sendKeys(confirmPasswordText);
	}

	public void clickPolicyCheckBox() {

		PolicyField.click();
	}

	public AccountSuccessPage clickContinueButton() {

		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void clicknewsLetterOption() {

		newsLetter.click();
	}

	public String retrieveDuplicareEmailAddressWarning() {

		String duplicateEmailAddressWarningMessageText = duplicateEmailAddressWarningMessage.getText();
		return duplicateEmailAddressWarningMessageText;
	}
	
	public String retrievePrivatePolicyWarningMessage() {

		String privatePolicyWarningMessageText = privacyPolicyWarningMessage.getText();
		return privatePolicyWarningMessageText;
	}
	
	public String retrieveFirstNameWarningMessage() {

		String firstNameWarningMessageText = firstNameWarningMessage.getText();
		return firstNameWarningMessageText;
	}
	
	public String retrieveLastNameWarningMessage() {

		String lastNameWarningMessageText = lastNameWarningMessage.getText();
		return lastNameWarningMessageText;
	}
	
	public String retrieveEmailWarningMessage() {

		String emailWarningMessageText = emailWarningMessage.getText();
		return emailWarningMessageText;
	}
	
	public String retrieveTelephoneNumberWarningMessage() {

		String telephonenumberWarningMessageText = telephoneNumberWarningMessage.getText();
		return telephonenumberWarningMessageText;
	}
	
	public String retrievePasswordWarningMessage() {

		String passwordWarningMessageText = passwordWarningMessage.getText();
		return passwordWarningMessageText;
	}
	
}
