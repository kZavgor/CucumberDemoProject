package com.serenemountain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class describes pages base interactions.
 */
abstract class BasePage {

    /**
     * Default element wait timeout.
     */
    private static final int DEFAULT_WEBDRIVER_WAIT = 60;

    /**
     * WebDriver instance.
     */
    private WebDriver webDriver;

    /**
     * Constructor.
     *
     * @param webDriver - webDriver instance.
     */
     BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Wait for element created in the DOM and visible.
     *
     * @param pageElement - element under test.
     * @return WebElement.
     */
    WebElement waitForElementVisible(final PageElement pageElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, DEFAULT_WEBDRIVER_WAIT);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageElement.getBy()));
    }

    /**
     * Find element in DOM.
     *
     * @param pageElement - element under test.
     * @return WebElement.
     */
    WebElement findElement(final PageElement pageElement) {
        return this.webDriver.findElement(pageElement.getBy());
    }
}
