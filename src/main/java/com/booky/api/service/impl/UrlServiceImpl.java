package com.booky.api.service.impl;

import com.booky.api.constants.Messages;
import com.booky.api.dao.UrlDAO;
import com.booky.api.exception.UrlServiceException;
import com.booky.api.model.URL;
import com.booky.api.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlDAO urlDAO;

    private String generate_hash() {
        Long random_number = ThreadLocalRandom.current().nextLong(1, 999999999);
        String dataset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder hash = new StringBuilder();
        while(random_number > 0) {
            Long remainder = random_number%62;
            hash.append(dataset.charAt(remainder.intValue()));
            random_number = random_number/62;
        }
        return hash.toString();
    }


    @Override
    public URL createUrl(URL url) {
        String shortUrl = generate_hash();
        URL existing_url = urlDAO.findUrlById(shortUrl);

        while(existing_url != null) {
            shortUrl = generate_hash();
            existing_url = urlDAO.findUrlById(shortUrl);
        }
        url.setShortUrl(shortUrl);
        url.setCreationTime(LocalDateTime.now());
        return urlDAO.createUrl(url);
    }

    @Override
    public String findUrl(String shortUrl) throws UrlServiceException {
        URL url = urlDAO.findUrlById(shortUrl);
        if(url == null) throw new UrlServiceException(Messages.URL_FIND_EXCEPTION_NO_SUCH_URL);

        if(url.getDaysToExpire() == -1) return url.getUrl();

        LocalDateTime expiryTime = url.getCreationTime().plusDays(url.getDaysToExpire().longValue());
        if(expiryTime.isAfter(LocalDateTime.now())) return url.getUrl();

        throw new UrlServiceException(Messages.URL_REDIRECTION_EXCEPTION_URL_EXPIRED);
    }

    @Override
    public void deleteUrl(String shortUrl) {
        urlDAO.deleteUrl(shortUrl);
    }
}
