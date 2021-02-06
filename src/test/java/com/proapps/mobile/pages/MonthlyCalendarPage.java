package com.proapps.mobile.pages;

import org.openqa.selenium.By;

import com.google.inject.Inject;
import com.proapps.mobile.support.World;

import io.appium.java_client.MobileElement;

public class MonthlyCalendarPage extends BasePage {

	@Inject
	public MonthlyCalendarPage(World world) {
		super(world);
	}
	
	public boolean isPassedDayAHolidayInCurrentMonth(int day) {
		MobileElement dayElement = driver.findElement(By.xpath("//android.view.View[@index='"+day+"']"));
		if (dayElement.getAttribute("content-desc").contains("item"))
			return true;
		else
			return false;
	}
	
	public void clickOnPassedDayInCurrentMonth(int day) {
		MobileElement dayElement = driver.findElement(By.xpath("//android.view.View[@index='"+day+"']"));
		elementUtils.waitUntilElementIsClickableAndClick(dayElement);
	}

}
