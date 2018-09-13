package com.serenemountain.models.api.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Data model describing response from geolocation api.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAppResponse {

    /**
     * Error message.
     *
     */
    private String errorMessage;

    /**
     * Current weather data object.
     *
     */
    @JsonProperty("currently")
    private Map<String, Object> currentWeather;

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getCurrentWeather() {
        return currentWeather;
    }

}
