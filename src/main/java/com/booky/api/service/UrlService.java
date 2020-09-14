package com.booky.api.service;

import com.booky.api.exception.UrlServiceException;
import com.booky.api.model.URL;

public interface UrlService {

    URL createUrl(URL url);

    String findUrl(String shortUrl) throws UrlServiceException;

    void deleteUrl(String shortUrl);
}
