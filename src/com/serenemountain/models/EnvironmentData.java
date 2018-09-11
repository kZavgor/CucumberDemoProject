package com.serenemountain.models;

public class EnvironmentData {

    public enum EnvironmentStage {
        DEV,
        STAGE,
        PROD
    }

    private EnvironmentStage stage;
    private String webURL;
    private String weatherAppApiURL;
    private String geolocationApiURL;
    private String forecastApiURL;

    public EnvironmentStage getStage() {
        return stage;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getWeatherAppApiURL() {
        return weatherAppApiURL;
    }

    public String getGeolocationApiURL() {
        return geolocationApiURL;
    }

    public String getForecastApiURL() {
        return forecastApiURL;
    }
}
