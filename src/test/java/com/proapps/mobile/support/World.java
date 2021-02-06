package com.proapps.mobile.support;

import java.util.Properties;

import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.guice.ScenarioScoped;

//Scenario scoped it is used to show Guice
//what will be the shared classes/variables and instantiate them only in here
@ScenarioScoped
public class World {
	public AppiumDriver<MobileElement> driver;
	public Properties properties;
	public SoftAssert softAssert;
}