package com.proapps.mobile.support;

import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileDriverManager {
	
	Properties prop;

	public MobileDriverManager(Properties prop) {
		this.prop = prop;
	}

	public AndroidDriver<MobileElement> getAndroidDriver() {
		try {
			final String URL_STRING = prop.getProperty("AppiumHubUrl");
			return new AndroidDriver<MobileElement>(new URL(URL_STRING), setAndroidCapabilites(prop));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public DesiredCapabilities setAndroidCapabilites(Properties prop) {
		DesiredCapabilities cap = DesiredCapabilities.android();
		cap.setCapability(org.openqa.selenium.remote.CapabilityType.BROWSER_NAME, prop.getProperty("androidBrowserName"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("platformName"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("platformVersion"));
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, prop.getProperty("androidAppActivity"));
		cap.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, prop.getProperty("androidAppWaitActivity"));
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, prop.getProperty("androidAppPackage"));
		cap.setCapability("avd", prop.getProperty("avd"));
		return cap;
	}
	
}