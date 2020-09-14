package com.booky.api.dao;

import com.booky.api.model.URL;


public interface UrlDAO {

	URL findUrlById(String id);

	URL createUrl(URL url);

	void deleteUrl(String shortUrl);
}
