package com.proapps.mobile.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.google.inject.Inject;

import io.appium.java_client.screenrecording.CanRecordScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	@Inject
	private ScenarioUtils ssUtils;
	
	@Inject
	private World world;
	

	@Before
	public void beforeScenario(Scenario scenario)
	{
		world.softAssert = new SoftAssert();
		world.properties = getConfigProperties();
		world.driver = new MobileDriverManager(getConfigProperties()).getAndroidDriver();
		ssUtils.scenario = scenario;
		if(System.getProperty("recordScreen")!=null && System.getProperty("recordScreen").equalsIgnoreCase("yes")) {
			((CanRecordScreen) world.driver).startRecordingScreen();
		}
	}
	
	@After
	public void afterScenario(Scenario scenario) throws Exception {
		
		if(System.getProperty("recordScreen")!=null && System.getProperty("recordScreen").equalsIgnoreCase("yes")) {
			String video = ((CanRecordScreen) world.driver).stopRecordingScreen();
			byte[] decodedVideo = Base64.getMimeDecoder().decode(video);
			scenario.embed(decodedVideo, "video/mp4", "Feature Recording");
		}
		if(scenario.isFailed()) {
			ssUtils.embedScreenshot("Failed Screenshot");
		}

		world.driver.quit();
	}
	
	private Properties getConfigProperties() 
	{ 
		Properties prop = new Properties(); 
		
		InputStream commonInputStream = getClass().getClassLoader().getResourceAsStream("commonConfig.properties");
		
		InputStream specificInputStream = getClass().getClassLoader().getResourceAsStream("androidConfig.properties");
		try
		{
			prop.load(commonInputStream); 
			prop.load(specificInputStream);
		} 
		catch (IOException e) 
		{
			e.printStackTrace(); 
		}
		return prop; 
	}
}