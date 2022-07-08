package com.tsvirko.urlshortener.service;

import org.springframework.http.ResponseEntity;

public interface ShortenerUrlService {
    /**
     * Method short the url
     * @param url Url for shortener
     * @return shortened url
     */
    String shortURL(String url);

    /**
     * Method generates randomUrl
     * @return random url "http://localhost:8080/url/kg74db98"
     */
    String generateURL();

    /**
     * Method get base url from DB for redirect
     * @param url Shortened url
     * @return redirect to base url
     */
    ResponseEntity<Void> getUrlForRedirect(String url);
}
