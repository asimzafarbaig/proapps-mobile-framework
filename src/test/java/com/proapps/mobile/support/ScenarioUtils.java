package com.proapps.mobile.support;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.inject.Inject;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.Scenario;

@ScenarioScoped
public class ScenarioUtils {
	
	@Inject
	private World world;
	
	Scenario scenario;
	
	public void embedScreenshot(String... screenshotName)
	{
		String screenshot = screenshotName.length > 0 ? screenshotName[0] : "Mobile Screenshot";
		scenario.embed(((TakesScreenshot)world.driver).getScreenshotAs(OutputType.BYTES), "image/png", screenshot);
	}
	
	public void write(String text)
	{
		scenario.write(text);
	}
}