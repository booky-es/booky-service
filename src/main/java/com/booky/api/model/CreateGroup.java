package com.booky.api.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotNull;


public class CreateGroup {

    private long id;

    @NotNull
    private String context;

    @JsonIgnore
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
}
