package com.serenemountain.models.api.geolocation;

import com.serenemountain.util.Configuration;
import com.serenemountain.util.IPostRequest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Optional;

/**
 * Weather app request model.
 *
 */
public class WeatherAppRequest implements IPostRequest{

    /**
     * Weather app request body data model.
     *
     */
    public static class GeolocationApiRequestBody {

        private String address;

        /**
         * Constructor.
         *
         * @param address - postcode.
         */
        private GeolocationApiRequestBody(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }
    }

    /**
     * Request body data.
     *
     */
    private GeolocationApiRequestBody geolocationApiRequestBody;

    /**
     * Constructor.
     *
     * @param address - postcode.
     */
    public WeatherAppRequest(final String address) {
        this.geolocationApiRequestBody = new GeolocationApiRequestBody(address);
    }

    /**
     * {@inheritDoc}.
     *
     */
    @Override
    public Object getBody() {
        return this.geolocationApiRequestBody;
    }

    /**
     * {@inheritDoc}.
     *
     */
    @Override
    public String getURL() {
        return Optional.ofNullable(Configuration.getEnvironmentData().getWeatherAppApiURL()).orElseThrow(() ->
                new IllegalArgumentException("Geolocation API link is not specified in configuration."));
    }

    /**
     * {@inheritDoc}.
     *
     */
    @Override
    public MediaType getMediaType() {
        return MediaType.APPLICATION_JSON_TYPE;
    }

    /**
     * {@inheritDoc}.
     *
     */
    @Override
    public MultivaluedMap<String, Object> getHeaders() {
        return null;
    }
}
