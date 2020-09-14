package com.booky.api.controller;

import com.booky.api.constants.Messages;
import com.booky.api.exception.BookyException;
import com.booky.api.exception.UrlRedirectException;
import com.booky.api.exception.UrlServiceException;
import com.booky.api.model.URL;
import com.booky.api.service.UrlService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@CrossOrigin
public class UrlController {

    private final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private UrlService urlService;

    /**
     * Controller method for the user to create a short url with expiry
     *
     * @return
     * @throws BookyException
     */
    @ApiOperation(value = "Create Short URL with expiry")
    @PostMapping("/api/v1/url")
    public URL createShortUrl(@RequestBody URL url) throws BookyException {
        LOGGER.info("createShortUrl : Begin ");
        try {
            url = urlService.createUrl(url);
        } catch (Exception exception) {
            LOGGER.error("Error while creating Short URL {}", exception);
            throw new BookyException(Messages.URL_CREATE_EXCEPTION);
        }
        LOGGER.info("createShortUrl : End ");
        return url;
    }

    /**
     * Controller method for the user to redirect from a short url to a url
     *
     * @return
     * @throws BookyException
     */
    @ApiOperation(value = "Redirect to URL from Short URL")
    @GetMapping("/r/{shortUrl}")
    public RedirectView redirectUrl(@PathVariable("shortUrl") String shortUrl) throws UrlRedirectException {
        LOGGER.info("redirectUrl : Begin ");
        RedirectView redirect;
        try {
            String url = urlService.findUrl(shortUrl);
            redirect = new RedirectView();
            redirect.setUrl(url);
            return redirect;
        } catch (UrlServiceException exception) {
            LOGGER.error("Error while redirecting Short URL {}", exception);
            throw new UrlRedirectException(exception.getMessage());
        } catch (Exception exception) {
            LOGGER.error("Error while redirecting Short URL {}", exception);
            throw new UrlRedirectException(Messages.URL_REDIRECTION_EXCEPTION);
        } finally {
            LOGGER.info("redirectUrl : End ");
        }
    }

}
