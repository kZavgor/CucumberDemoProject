package com.serenemountain.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Objects;

/**
 * Http client util class encapsulating Jersey http client.
 *
 */
public final class HttpClient {

    /**
     * Local http jersey client instance.
     *
     */
    private static Client httpClient;

    /**
     * Constructor.
     *
     */
    private HttpClient() {

    }

    /**
     * Get and, if required, initialize http client.
     *
     * @return Client.
     */
    private static Client getClient() {
        if (Objects.isNull(httpClient)) {
            httpClient = ClientBuilder.newBuilder().build();
        }
        return httpClient;
    }

    /**
     * Do POST request.
     *
     * @param request - request data.
     * @param responseType - body unmarshalling class.
     * @param <T> - type of class body will be unmarshalled.
     * @return Unmarshalled body of type T.
     */
    public static <T> T doPost(final IPostRequest request, final Class<T> responseType) {

        Response response = getClient().target(request.getURL())
                .request()
                .headers(request.getHeaders())
                .accept(request.getMediaType())
                .post(Entity.entity(request.getBody(), request.getMediaType()));

        return response.readEntity(responseType);
    }
}
