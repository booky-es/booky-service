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
    private Integer daysToExpire;

    private LocalDateTime creationTime;

    private boolean expired;

    public URL() { }

    public URL(String url, Integer daysToExpire) {
        this.url = url;
        this.daysToExpire = daysToExpire;
    }

    public String getUrl() {  return url;  }

    public void setUrl(String url) {  this.url = url; }

    public Integer getDaysToExpire() {  return daysToExpire;  }

    public void setDaysToExpire(Integer daysToExpire) {  this.daysToExpire = daysToExpire; }

    @JsonIgnore
    public LocalDateTime getCreationTime() {  return creationTime; }

    public void setCreationTime(LocalDateTime creationTime) {  this.creationTime = creationTime; }

    public boolean isExpired() {  return expired; }

    public void setExpired(boolean expired) {  this.expired = expired; }

    @JsonIgnore
    public String getShortUrl() {  return shortUrl; }

    public void setShortUrl(String shortUrl) {  this.shortUrl = shortUrl; }

}
