package com.booky.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    @GetMapping("/status")
    public String getStatus() {
        return "OK";
    }

}
