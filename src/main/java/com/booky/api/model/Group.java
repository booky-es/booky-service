package com.booky.api.model;


import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "groups")
public class Group {

    @Transient
    public static final String SEQUENCE_NAME = "groups_sequence";

    @Id
    @JsonIgnore
    private long id;

    private String context;

    @JsonIgnore
    private List<BigInteger> adminIds = new ArrayList<>();

    @JsonIgnore
    private List<String> cardIds = new ArrayList<>();

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

    public List<BigInteger> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(List<BigInteger> adminIds) {
        this.adminIds = adminIds;
    }

    public List<String> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<String> cardIds) {
        this.cardIds = cardIds;
    }
}
