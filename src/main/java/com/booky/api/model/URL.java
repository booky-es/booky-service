package com.booky.api.model;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "urls")
public class URL {

    @NotNull
    private String url;

    @Id
    private String shortUrl;

    @NotNull
    private LocalDateTime expiryDate;

    private LocalDateTime creationTime;

    public URL() { }

    public URL(String url, LocalDateTime expiryDate) {
        this.url = url;
        this.expiryDate = expiryDate;
    }

    public String getUrl() {  return url;  }

    public void setUrl(String url) {  this.url = url; }

    @JsonIgnore
    public LocalDateTime getCreationTime() {  return creationTime; }

    public void setCreationTime(LocalDateTime creationTime) {  this.creationTime = creationTime; }

    @JsonIgnore
    public String getShortUrl() {  return shortUrl; }

    public void setShortUrl(String shortUrl) {  this.shortUrl = shortUrl; }

    public LocalDateTime getExpiryDate() {  return expiryDate; }

    public void setExpiryDate(LocalDateTime expiryDate) {  this.expiryDate = expiryDate; }
}
