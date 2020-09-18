package com.booky.api.model;


import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "groups")
public class Group {

    @Transient
    public static final String SEQUENCE_NAME = "groups_sequence";

    @Id
    private long id;

    @NotNull
    private String context;

    private List<BigInteger> adminIds = new ArrayList<>();

    private List<Long> cardIds = new ArrayList<>();

    private List<Long> cardQueueIds = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @JsonIgnore
    public List<BigInteger> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(List<BigInteger> adminIds) {
        this.adminIds = adminIds;
    }

    @JsonIgnore
    public List<Long> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Long> cardIds) {
        this.cardIds = cardIds;
    }

    @JsonIgnore
    public List<Long> getCardQueueIds() { return cardQueueIds; }

    public void setCardQueueIds(List<Long> cardQueueIds) { this.cardQueueIds = cardQueueIds; }
}
