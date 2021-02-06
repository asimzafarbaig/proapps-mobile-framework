package com.proapps.mobile.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.google.inject.Inject;
import com.proapps.mobile.support.World;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {

	@Inject
	public HomePage(World world) {
		super(world);
	}
	
	@AndroidFindBy(accessibility = "Show Calendar List and Settings drawer")
	private MobileElement topLeftMenuButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,'Month view')]")
	private MobileElement monthViewOptionInMainMenu;
	
	@AndroidFindBy(accessibility = "next page")
	private MobileElement nextPageOnNewlyLaunchedItems;
	
	@AndroidFindBy(id = "com.google.android.calendar:id/oobe_done_button")
	private MobileElement gotItButtonOnNewlyLaunchedItems;
	
	@AndroidFindBy(accessibility = "Create new event and more")
	private MobileElement createNewEventButton;
	
	public void clickOnMenuButtonOnTopLeft() {
		elementUtils.waitUntilElementIsVisible(topLeftMenuButton);
		elementUtils.waitUntilElementIsClickableAndClick(topLeftMenuButton);
	}
	
	public void clickOnMonthViewInTheMainMenu() {
		elementUtils.waitUntilElementIsClickableAndClick(monthViewOptionInMainMenu);
	}

	public void scrollThroughNewlyLaunchedItems() {
		elementUtils.waitUntilElementIsClickableAndClick(nextPageOnNewlyLaunchedItems);
		elementUtils.waitUntilElementIsClickableAndClick(nextPageOnNewlyLaunchedItems);
		elementUtils.waitUntilElementIsClickableAndClick(gotItButtonOnNewlyLaunchedItems);
	}
	
	public boolean isMeetingWithTitlePresent(String title) {
		elementUtils.waitUntilElementIsClickable(createNewEventButton);
		List<MobileElement> elementsWithTitle = driver.findElements(By.xpath("//android.view.View[contains(@content-desc,'"+title+"')]"));
		if (elementsWithTitle.size() == 0)
			return false;
		else
			return true;
	}

}
