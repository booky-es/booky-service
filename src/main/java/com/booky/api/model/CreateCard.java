package com.booky.api.model;

import com.booky.api.constants.CardStatus;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotNull;


public class CreateCard {

    private long id;

    @NotNull
    private String title;

    @NotNull
    private String url;

    private String shortUrl;

    private String image;

    @NotNull
    private String description;

    private CardStatus status;

    @NotNull
    private long groupId;

    public CreateCard() {}

    public CreateCard(CardQueue cardQueue) {
        this.id = cardQueue.getCardId();
        this.title = cardQueue.getTitle();
        this.shortUrl = cardQueue.getShortUrl();
        this.url = cardQueue.getUrl();
        this.image = cardQueue.getImage();
        this.description = cardQueue.getDescription();
        this.groupId = cardQueue.getGroupId();
        this.status = cardQueue.getStatus();
    }


    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getShortUrl() { return shortUrl; }

    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {  return url; }

    public void setUrl(String url) {  this.url = url; }

    public String getImage() { return image;  }

    public void setImage(String image) {  this.image = image; }

    public long getGroupId() {  return groupId; }

    public void setGroupId(long groupId) { this.groupId = groupId; }

    @JsonIgnore
    public CardStatus getStatus() {  return status; }

    public void setStatus(CardStatus status) { this.status = status; }

    public String getDescription() {  return description; }

    public void setDescription(String description) { this.description = description; }
}
