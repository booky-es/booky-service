package com.booky.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class Card {

    public static final String SEQUENCE_NAME = "cards_sequence";

    @Id
    private long id;

    private String url;

    private String title;

    private String shortUrl;

    private String image;

    private long groupId;

    private String description;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getShortUrl() {  return shortUrl; }

    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public long getGroupId() { return groupId; }

    public void setGroupId(long groupId) { this.groupId = groupId; }
}
