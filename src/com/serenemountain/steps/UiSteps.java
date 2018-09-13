package com.serenemountain.steps;


import com.serenemountain.pages.ForecastTable;
import com.serenemountain.pages.MainPage;
import com.serenemountain.util.Configuration;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;

/**
 * Class describing ui cucumber steps.
 */
public class UiSteps {

    /**
     * WebDriver instance.
     */
    private WebDriver webDriver;

    /**
     * Main page instance;
     */
    private MainPage mainPage;

    /**
     * Forecast table instance;
     */
    private ForecastTable forecastTable;

    /**
     * Navigate to main page.
     */
    @Given("^I am on a base page$")
    public void iAmOnABasePage() {
        String homeURL = Optional.ofNullable(Configuration.getEnvironmentData().getWebURL())
                .orElseThrow(() -> new IllegalArgumentException("Weather app website url is not present in config."));

        //initialize block.
        WebDriverManager.chromedriver().setup();
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        this.webDriver.navigate().to(homeURL);

        this.mainPage = new MainPage(this.webDriver);
        this.forecastTable = new ForecastTable(this.webDriver);
    }

    /**
     * Enter post code and submit form.
     *
     * @param postCode - post code value.
     */
    @When("^I enter \"([^\"]*)\" post code$")
    public void iEnterPostCode(final String postCode) {
        this.mainPage.submitPostCode(postCode);
    }

    /**
     * Validate error message is shown and has valid text.
     *
     * @param errorText - expected error text.
     */
    @Then("^I should see \"([^\"]*)\" error message$")
    public void iSeeErrorMessage(final String errorText) {
        this.mainPage.validateErrorText(errorText);
    }

    /**
     * Validate if forecast table is displayed.
     *
     */
    @Then("^I should see weather forecast table$")
    public void iSeeWeatherForeCastTable() {
        this.forecastTable.validateForecastTableDisplayed();
    }

    /**
     * Validate if forecast table contains Time value in expected format.
     *
     * @param expectedDateFormat - expected time format.
     */
    @Then("^I current time having \"([^\"]*)\" format$")
    public void validateCurrentTimeFormatInForecastTable(final String expectedDateFormat) {
        this.forecastTable.validateCurrentTimeHasExpectedFormat(expectedDateFormat);
    }

    /**
     * Validate if forecast table does not contain properties with empty values.
     *
     */
    @Then("^Forecast should not contain properties without values$")
    public void validateForecastTableDoesNotHaveEmptyProperties() {
        this.forecastTable.validateForecastTableHasNoEmptyProperties();
    }

    /**
     * Validate if Weather Checker header is displayed.
     *
     */
    @Then("^I see Weather Checker header$")
    public void iSeeWeatherCheckerHeader() {
        this.mainPage.validateWeatherCheckerHeaderIsVisible();
    }

    /**
     * Validate if postcode input box is displayed.
     *
     */
    @Then("^I see postcode input box$")
    public void iSeeInputBox() {
        this.mainPage.validateSearchBoxIsVisible();
    }

    /**
     * Validate if submit button is displayed.
     *
     */
    @Then("^I see submit button$")
    public void iSeeSubmitButton() {
        this.mainPage.validateSubmitButtonIsVisible();
    }

    /**
     * Validates if property under test is displayed.
     *
     * @param propertyName - property under test name.
     */
    @Then("^I should see \"([^\"]*)\" weather property$")
    public void iSeeWeatherPropertyInForecastTable(final String propertyName) {
        this.forecastTable.validateForecastTableHasProperty(propertyName);
    }

    /**
     * Tear down method to execute after each test. Closes webdriver is opened.
     */
    @After
    public void tearDown() {

        if (this.webDriver != null) {
            this.webDriver.quit();
        }
    }
}