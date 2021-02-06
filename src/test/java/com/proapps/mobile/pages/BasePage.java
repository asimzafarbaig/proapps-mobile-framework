package com.proapps.mobile.pages;

import org.openqa.selenium.support.PageFactory;

import com.google.inject.Inject;
import com.proapps.mobile.support.ActionUtils;
import com.proapps.mobile.support.ElementUtils;
import com.proapps.mobile.support.World;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class BasePage {
	
	protected AppiumDriver<MobileElement> driver;
	
	@Inject
	protected ElementUtils elementUtils;
	
	@Inject
	protected ActionUtils actionUtils;
	
	// Initiate the Page Factory and create as abstract class, so
	// you can use for all the other Page Objects
	public BasePage(World world) {
		this.driver = world.driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
		
	}
}