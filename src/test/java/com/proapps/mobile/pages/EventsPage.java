package com.proapps.mobile.pages;

import com.google.inject.Inject;
import com.proapps.mobile.support.World;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EventsPage extends BasePage {

	@Inject
	public EventsPage(World world) {
		super(world);
	}
	
	@AndroidFindBy(accessibility = "Nothing planned. Tap to create.")
	private MobileElement createPlanButton;
	
	public void clickOnCreatePlanButton() {
		elementUtils.waitUntilElementIsVisible(createPlanButton);
		elementUtils.waitUntilElementIsClickableAndClick(createPlanButton);
	}

}
