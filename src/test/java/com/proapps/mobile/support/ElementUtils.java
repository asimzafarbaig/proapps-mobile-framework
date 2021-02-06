package com.proapps.mobile.support;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ElementUtils {

    private AppiumDriver<MobileElement> driver;

    private int DEFAULT_TIMEOUT = 30;
    
    @Inject
    public ElementUtils(World world) {
        this.driver = world.driver;
    }

    /***
     * Waits for the passed timeOutInSeconds for the element to be clickable
     * @param element The element whose status should be checked as clickable
     * @param timeOutInSeconds (Optional)
     */
    public void waitUntilElementIsClickable(WebElement element, int... timeOutInSeconds) {
        int timeOutToUse = timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : DEFAULT_TIMEOUT;
        new WebDriverWait(driver, timeOutToUse).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementIsClickableAndClick(WebElement element, int... timeOutInSeconds) {
        waitUntilElementIsClickable(element, timeOutInSeconds);
        element.click();
    }
    
    /***
     * Waits for the passed timeOutInSeconds for the element to be visible
     * @param element The element whose status should be checked as visible
     * @param timeOutInSeconds (Optional)
     */
    public void waitUntilElementIsVisible(WebElement element, int... timeOutInSeconds) {
        int timeOutToUse = timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : DEFAULT_TIMEOUT;
        new WebDriverWait(driver, timeOutToUse).until(ExpectedConditions.visibilityOf(element));
    }
	
	/***
     * Waits for the passed timeOutInSeconds for the element to not be stale
     * @param element The element whose status should be checked to not be stale
     * @param timeOutInSeconds (Optional)
     */
	public void waitUntilElementIsNotStale(WebElement element, int... timeOutInSeconds) {
		int timeOutToUse = timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : DEFAULT_TIMEOUT;
		new WebDriverWait(driver, timeOutToUse).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
	}
	
	/***
     * Waits for the passed timeOutInSeconds for the element to not be stale, wait for it to be 
     * clickable and click on it
     * @param element The element whose status should be checked as non stale and clicked
     * @param timeOutInSeconds (Optional)
     */
	public void waitUntilElementIsNotStaleAndClick(WebElement element, int... timeOutInSeconds) {
		waitUntilElementIsNotStale(element, timeOutInSeconds);
		waitUntilElementIsClickableAndClick(element,timeOutInSeconds);
	}
	
	public void sendKeys(WebElement element, String keysToSend, int... timeOutInSeconds) {
		waitUntilElementIsVisible(element, timeOutInSeconds);
		element.clear();
		element.sendKeys(keysToSend);
	}
	
}