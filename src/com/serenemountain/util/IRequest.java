package com.serenemountain.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Interface describing framework specific request object.
 *
 */
public interface IRequest {

    /**
     * Get request url.
     *
     * @return String.
     */
    String getURL();

    /**
     * Get request accept media type.
     *
     * @return MediaType.
     */
    MediaType getMediaType();

    /**
     * Get request headers.
     *
     * @return Map headers object
     */
    MultivaluedMap<String, Object> getHeaders();
}
