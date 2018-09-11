package com.serenemountain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final int DEFAULT_WEBDRIVER_WAIT = 60;

    private WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebElement waitForElementVisible(final PageElement pageElement) {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, DEFAULT_WEBDRIVER_WAIT);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageElement.getBy()));
    }

    protected WebElement findElement(final PageElement pageElement) {
        return this.webDriver.findElement(pageElement.getBy());
    }
}
