package com.proapps.mobile.pages;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import org.openqa.selenium.By;

import com.google.inject.Inject;
import com.proapps.mobile.support.World;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreateMeetingPage extends BasePage {

	@Inject
	public CreateMeetingPage(World world) {
		super(world);
	}
	
	@AndroidFindBy(id = "com.google.android.calendar:id/title")
	private MobileElement titleTextbox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,'Start time')]")
	private MobileElement startTimeSelector;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,'End time')]")
	private MobileElement endTimeSelector;
	
	@AndroidFindBy(id = "android:id/toggle_mode")
	private MobileElement toggleToKeyboardButtonOnTimeSelectionPopup;
	
	@AndroidFindBy(id = "android:id/input_hour")
	private MobileElement inputHourTextboxOnTimeSelectionPopup;
	
	@AndroidFindBy(id = "android:id/input_minute")
	private MobileElement inputMinuteTextboxOnTimeSelectionPopup;
	
	@AndroidFindBy(id = "android:id/am_pm_spinner")
	private MobileElement timeOfTheDayDropdownOnTimeSelectionPopup;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='AM']")
	private MobileElement amOnTimeOfTheDayDropdownOnTimeSelectionPopup;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='PM']")
	private MobileElement pmOnTimeOfTheDayDropdownOnTimeSelectionPopup;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private MobileElement okButtonOnTimeSelectionPopup;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Does not repeat']")
	private MobileElement recurrenceOption;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Custom…']")
	private MobileElement customOptionOnRecurrenceMenu;
	
	@AndroidFindBy(id = "com.google.android.calendar:id/end_options_label")
	private MobileElement endOptionsLabelInCustomRecurrence;
	
	@AndroidFindBy(id = "com.google.android.calendar:id/date_duration_radio")
	private MobileElement dateDurationRadioInCustomRecurrence;
	
	@AndroidFindBy(id = "com.google.android.calendar:id/date_duration_text")
	private MobileElement dateDurationTextInCustomRecurrence;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Done']")
	private MobileElement doneButtonInCustomRecurrence;
	
	@AndroidFindBy(accessibility = "Next month")
	private MobileElement nextMonthButtonInCustomRecurrenceCalendar;
	
	@AndroidFindBy(id = "com.google.android.calendar:id/save")
	private MobileElement saveButton;
	
	public void addMeetingTitle(String title) {
		elementUtils.waitUntilElementIsClickable(titleTextbox);
		titleTextbox.sendKeys(title);
	}
	
	public void setMeetingStartTime(String startTime) throws InterruptedException {
		elementUtils.waitUntilElementIsClickableAndClick(startTimeSelector);
		selectTimeOnTimeSelectionPopup(startTime);
	}
	
	public void setMeetingEndTime(String endTime) throws InterruptedException {
		elementUtils.waitUntilElementIsClickableAndClick(endTimeSelector);
		selectTimeOnTimeSelectionPopup(endTime);
	}
	
	private void selectTimeOnTimeSelectionPopup(String time) {
		clickOnToggleToKeyboardOnTimeSelectionPopup();
		enterTheTimeOnTimeSelectionPopUp(time);
		clickOnOkButtonOnTimeSelectionPopup();
	}
	
	private void clickOnToggleToKeyboardOnTimeSelectionPopup() {
		elementUtils.waitUntilElementIsClickableAndClick(toggleToKeyboardButtonOnTimeSelectionPopup);
	}
	
	private void clickOnOkButtonOnTimeSelectionPopup() {
		elementUtils.waitUntilElementIsClickableAndClick(okButtonOnTimeSelectionPopup);
	}
	
	/***
	 * Method to select the time on the time selection popup
	 * @param time in the format h:mm AM/PM
	 */
	private void enterTheTimeOnTimeSelectionPopUp(String time) {
		String hour = time.split(":")[0];
		String minute = time.split(":")[1].split(" ")[0];
		String partOfTheDay = time.split(" ")[1];
		elementUtils.waitUntilElementIsClickable(inputHourTextboxOnTimeSelectionPopup);
		elementUtils.sendKeys(inputHourTextboxOnTimeSelectionPopup, hour);
		elementUtils.sendKeys(inputMinuteTextboxOnTimeSelectionPopup, minute);
		elementUtils.waitUntilElementIsClickableAndClick(timeOfTheDayDropdownOnTimeSelectionPopup);
		switch (partOfTheDay.toUpperCase()) {
		case "AM" : {
			elementUtils.waitUntilElementIsNotStaleAndClick(amOnTimeOfTheDayDropdownOnTimeSelectionPopup);
			break;
		}
		case "PM" : {
			elementUtils.waitUntilElementIsNotStaleAndClick(pmOnTimeOfTheDayDropdownOnTimeSelectionPopup);
			break;
		}
		default : {
			throw new InvalidParameterException("Only AM or PM can be the Time Of the Day");
		}
		}
	}
	
	public void selectCustomRecurrenceOption() {
		elementUtils.waitUntilElementIsClickableAndClick(recurrenceOption);
		elementUtils.waitUntilElementIsClickableAndClick(customOptionOnRecurrenceMenu);
	}
	
	public void setCustomRecurrenceDateOfMonthsFromToday(int noOfMonths) {
		elementUtils.waitUntilElementIsVisible(endOptionsLabelInCustomRecurrence);
		actionUtils.scrollElementToTheTop(endOptionsLabelInCustomRecurrence);
		elementUtils.waitUntilElementIsClickableAndClick(dateDurationRadioInCustomRecurrence);
		elementUtils.waitUntilElementIsClickableAndClick(dateDurationTextInCustomRecurrence);
		for (int i = 1 ; i <= noOfMonths ; i++) {
			elementUtils.waitUntilElementIsClickableAndClick(nextMonthButtonInCustomRecurrenceCalendar);
		}
		int dateToSelect = LocalDate.now().getDayOfMonth();
		elementUtils.waitUntilElementIsClickableAndClick(driver.findElement(By.xpath("//android.view.View[@text='"+dateToSelect+"']")));
		elementUtils.waitUntilElementIsClickableAndClick(okButtonOnTimeSelectionPopup);
	}
	
	public void clickOnDoneButtonInCustomRecurrence() {
		elementUtils.waitUntilElementIsClickableAndClick(doneButtonInCustomRecurrence);
	}
	
	public void clickOnSaveButton() {
		elementUtils.waitUntilElementIsClickableAndClick(saveButton);
	}

}
