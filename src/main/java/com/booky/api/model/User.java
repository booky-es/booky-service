package com.booky.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "users")
public class User {

    @Id
    private BigInteger userId;
    private String firstName;
    private String lastName;
    private String email;

    public User() {}

    public User(BigInteger userId, String email, String lastName, String firstName) {
        this.userId = userId;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
