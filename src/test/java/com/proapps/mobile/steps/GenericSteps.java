package com.proapps.mobile.steps;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import com.google.inject.Inject;
import com.proapps.mobile.pages.EventsPage;
import com.proapps.mobile.pages.HomePage;
import com.proapps.mobile.pages.MonthlyCalendarPage;
import com.proapps.mobile.support.ScenarioUtils;
import com.proapps.mobile.support.World;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class GenericSteps {
	
	@Inject
	private World world;
	
	@Inject
	private ScenarioUtils sUtils;
	
	@Inject
	private HomePage homePage;
	
	@Inject
	private MonthlyCalendarPage monthlyCalendarPage;
	
	@Inject
	private EventsPage eventsPage;
	
	@Given("^I have launched the Calendar App$")
	public void i_have_launched_the_Calendar_App() {
		world.driver.activateApp(world.properties.getProperty("androidAppPackage"));
		sUtils.write("The Calendar App is Activated");
	}
	
	@And("^I scroll through the newly launched items$")
	public void iScrollThroughTheNewlyLaunchedItems() {
		homePage.scrollThroughNewlyLaunchedItems();
	}

	@And("^I select the monthly view from the top left menu$")
	public void iSelectTheMonthlyViewFromTheTopLeftMenu() {
		homePage.clickOnMenuButtonOnTopLeft();
		homePage.clickOnMonthViewInTheMainMenu();
		sUtils.write("Selected the Monthly View");
		sUtils.embedScreenshot("The Monthly View of the Calendar");
	}

	@Then("^I select the next working \"([^\"]*)\" and click on tap to create$")
	public void iSelectTheNextWorkingDay(String day) throws InvalidParameterException {
		DayOfWeek dayOfWeek;
		switch (day.toUpperCase()) {
		case "MONDAY" : {
			dayOfWeek = DayOfWeek.MONDAY;
			break;
		}
		case "TUESDAY" : {
			dayOfWeek = DayOfWeek.TUESDAY;
			break;
		}
		case "WEDNESDAY" : {
			dayOfWeek = DayOfWeek.WEDNESDAY;
			break;
		}
		case "THURSDAY" : {
			dayOfWeek = DayOfWeek.THURSDAY;
			break;
		}
		case "FRIDAY" : {
			dayOfWeek = DayOfWeek.FRIDAY;
			break;
		}
		default : {
			throw new InvalidParameterException("The day passed in incorrect. It should be between Monday to Friday");
		}
		}
		LocalDate nextDate = LocalDate.now();
		for (int i = 0 ; i < 4 ; i++) {
			nextDate = nextDate.with(TemporalAdjusters.next(dayOfWeek));
			int dayOfTheMonth = nextDate.getDayOfMonth();
			if (!monthlyCalendarPage.isPassedDayAHolidayInCurrentMonth(dayOfTheMonth))
			{
				monthlyCalendarPage.clickOnPassedDayInCurrentMonth(dayOfTheMonth);
				break;
			}
		}
		eventsPage.clickOnCreatePlanButton();
		sUtils.write("Clicked on Create New Plan Button for the next working "+day.toUpperCase());
		sUtils.embedScreenshot();
	}

	
	
	

}
