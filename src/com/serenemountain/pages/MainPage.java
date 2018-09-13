package com.serenemountain.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Class describing base interaction with main page and it model.
 *
 */
public class MainPage extends BasePage {

    /**
     * Weather checker header.
     *
     */
    private final PageElement weatherCheckerHeader = new PageElement(SelectorType.CSS, ".header");

    /**
     * Postcode input.
     *
     */
    private final PageElement searchBox = new PageElement(SelectorType.CSS, "#searchLocation input");

    /**
     * Form submit button.
     *
     */
    private final PageElement submitButton = new PageElement(SelectorType.CSS, "#searchLocation button");

    /**
     * Error placeholder.
     *
     */
    private final PageElement errorMessage = new PageElement(SelectorType.CSS, "#root > div > h1");

    /**
     * Constructor.
     *
     * @param webDriver - webdriver instance.
     */
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Fill and send postcode under test.
     *
     * @param postcode - postcode under test.
     */
    public void submitPostCode(final String postcode) {
        waitForElementVisible(searchBox).sendKeys(postcode);
        findElement(submitButton).click();
    }

    /**
     * Validate error message is shown and has valid text.
     *
     * @param expectedErrorText - expected error text.
     */
    public void validateErrorText(final String expectedErrorText) {
        WebElement errorElement = waitForElementVisible(errorMessage);
        Assert.assertEquals("Incorrect error message displayed", errorElement.getText(), expectedErrorText);
    }

    /**
     * Validate if Weather Checker header is displayed.
     *
     */
    public void validateWeatherCheckerHeaderIsVisible() {
        Assert.assertTrue("Weather checker header is not visible",
                findElement(weatherCheckerHeader).isDisplayed());
    }

    /**
     * Validate if postcode input box is displayed.
     *
     */
    public void validateSearchBoxIsVisible() {
        Assert.assertTrue("Search box is not visible", findElement(searchBox).isDisplayed());
    }

    /**
     * Validate if submit button is displayed.
     *
     */
    public void validateSubmitButtonIsVisible() {
        Assert.assertTrue("Submit button is not visible", findElement(submitButton).isDisplayed());
    }
}
