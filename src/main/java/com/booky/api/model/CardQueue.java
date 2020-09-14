package com.booky.api.model;

import com.booky.api.constants.CardStatus;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards_queue")
public class CardQueue {

    public static final String SEQUENCE_NAME = "cards_queue_sequence";

    @Id
    @JsonIgnore
    private long id;

    private String url;

    private String shortUrl;

    private String title;

    private String image;

    private long groupId;

    private String description;

    private long cardId;

    private CardStatus status;

    public CardQueue() {}

    public CardQueue(Card card, CardStatus cardStatus) {
        this.url = card.getUrl();
        this.shortUrl = card.getShortUrl();
        this.title = card.getTitle();
        this.image = card.getImage();
        this.groupId = card.getGroupId();
        this.description = card.getDescription();
        this.status=cardStatus;
        this.cardId = card.getId();
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getShortUrl() { return shortUrl; }

    public void setShortUrl(String shortUrl) {  this.shortUrl = shortUrl; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public long getGroupId() { return groupId; }

    public void setGroupId(long groupId) { this.groupId = groupId; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public long getCardId() {  return cardId; }

    public void setCardId(long cardId) {  this.cardId = cardId; }

    public CardStatus getStatus() {  return status; }

    public void setStatus(CardStatus status) {  this.status = status; }
}
