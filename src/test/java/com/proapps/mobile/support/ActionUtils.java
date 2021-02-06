package com.proapps.mobile.support;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ActionUtils {

    private WebDriver driver;

    @Inject
    public ActionUtils(World world) {
        this.driver = world.driver;
    }
	
    /***
     * Scrolls the passed element to the Top of the screen excluding the device's top menu
     * @param sourceElement The element that should be scrolled to the top
     */
	@SuppressWarnings({ "rawtypes" })
	public void scrollElementToTheTop(MobileElement sourceElement) {
		TouchAction action = new TouchAction((PerformsTouchActions) driver);
		int sourceXCoordinate = sourceElement.getLocation().getX();
		int sourceYCoordinate = sourceElement.getLocation().getY();
		action.press(new PointOption().withCoordinates(sourceXCoordinate, sourceYCoordinate)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(4))).moveTo(new PointOption().withCoordinates(sourceXCoordinate, 35)).release().perform();
		}
}