package com.booky.api.dao.impl;

import com.booky.api.dao.UrlDAO;
import com.booky.api.model.URL;
import com.booky.api.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlDAOImpl implements UrlDAO {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public URL findUrlById(String id) {
        return urlRepository.findById(id).orElse(null);
    }

    @Override
    public URL createUrl(URL url) {
        return urlRepository.save(url);
    }

    @Override
    public void deleteUrl(String shortUrl) {  urlRepository.deleteById(shortUrl); }
}
