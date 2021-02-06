package com.proapps.mobile.steps;

import org.testng.Assert;

import com.google.inject.Inject;
import com.proapps.mobile.pages.CreateMeetingPage;
import com.proapps.mobile.pages.HomePage;
import com.proapps.mobile.support.ScenarioUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CreateMeetingSteps {
	
	@Inject
	private ScenarioUtils sUtils;
	
	@Inject
	private CreateMeetingPage createMeetingPage;
	
	@Inject
	private HomePage homePage;

	@Then("^I give the meeting title as \"([^\"]*)\"$")
	public void iGiveTheMeetingTitleAs(String title) {
		createMeetingPage.addMeetingTitle(title);
		sUtils.write("Added the Meeting Title as "+title);
	}

	@And("^I set the meeting time from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void iSetTheMeetingTimeFromTo(String startTime, String endTime) throws InterruptedException {
		createMeetingPage.setMeetingStartTime(startTime);
		sUtils.write("Set the Meeting Start Time as " + startTime);
		createMeetingPage.setMeetingEndTime(endTime);
		sUtils.write("Set the Meeting End Time as " + endTime);
	}

	@And("^I set the meeting recurrence for \"([^\"]*)\" months$")
	public void iSetTheMeetingRecurrenceForMonths(String noOfMonths) {
		createMeetingPage.selectCustomRecurrenceOption();
		createMeetingPage.setCustomRecurrenceDateOfMonthsFromToday(Integer.parseInt(noOfMonths));
		createMeetingPage.clickOnDoneButtonInCustomRecurrence();
	}

	@Then("^I save the meeting$")
	public void iSaveTheMeeting() {
		createMeetingPage.clickOnSaveButton();
		sUtils.write("Saved the Meeting");
	}

	@And("^I check if meeting with title \"([^\"]*)\" is present$")
	public void iCheckIfMeetingWithTitleIsPresent(String title) {
		Assert.assertTrue(homePage.isMeetingWithTitlePresent(title),"The Meeting with Title "+title+ " is Not Present");
		sUtils.write("Meeting with Title "+title+ " is Present");
		sUtils.embedScreenshot();
	}

}
