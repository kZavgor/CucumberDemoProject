package com.serenemountain.steps;

import com.serenemountain.models.api.geolocation.WeatherAppRequest;
import com.serenemountain.models.api.geolocation.WeatherAppResponse;
import com.serenemountain.util.HttpClient;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Class describing the feature api steps definitions.
 *
 */
public class ApiSteps {

    /**
     * Weather app api response steps sharable instance.
     *
     */
    private WeatherAppResponse weatherAppResponse;

    /**
     * Generate and sent weather app request.
     *
     * @param postCode - postcode.
     */
    @Given("^I send \"([^\"]*)\" post code to weather app$")
    public void iSendPostCodeToGeolocationApi(final String postCode) {
        WeatherAppRequest weatherAppRequest = new WeatherAppRequest(postCode);
        this.weatherAppResponse = HttpClient.doPost(weatherAppRequest, WeatherAppResponse.class);
    }

    /**
     * Validate if weather app response has error message and message contains text under test.
     *
     * @param errorMessage - error message expected test.
     */
    @Then("^I receive an error message \"([^\"]*)\"$")
    public void validateGeolocationApiHasErrorMessage(final String errorMessage) {
        Assert.assertNotNull("Geolocation API response does not have error message.",
                this.weatherAppResponse.getErrorMessage());

        Assert.assertTrue("Geolocation API has incorrect message.",
                this.weatherAppResponse.getErrorMessage().contains(errorMessage));
    }

    /**
     * Validate if response has current weather data.
     *
     */
    @Then("^I receive current weather forecast$")
    public void validateCurrentForecastIsReturned() {
        Assert.assertNotNull("Weather app response does not have error message.",
                this.weatherAppResponse.getCurrentWeather());
    }

    /**
     * Validate if weather response has current weather property.
     *
     * @param propertyUnderTest - property under test.
     */
    @Then("^I should see \"([^\"]*)\" property returned in weather forecast$")
    public void validateCurrentForecastIsReturned(final String propertyUnderTest) {
        Assert.assertTrue(String.format("Weather app response does not have %s property in current weather forecast",
                propertyUnderTest), this.weatherAppResponse.getCurrentWeather().containsKey(propertyUnderTest));
    }
}
