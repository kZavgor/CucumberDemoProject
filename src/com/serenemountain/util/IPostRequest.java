package com.serenemountain.util;

/**
 * Interface describing framework specific POST request object.
 *
 * @param <T> - type of request body data model.'
 */
public interface IPostRequest<T> extends IRequest {

    /**
     * Return request body.
     *
     * @return - request body of type T.
     */
    T getBody();

}
