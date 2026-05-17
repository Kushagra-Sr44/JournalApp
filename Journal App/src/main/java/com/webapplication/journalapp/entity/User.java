package com.webapplication.journalapp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String userName;


    private String password;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @DBRef
    private List<JournalEntry> journalList = new ArrayList<>();
private List<String> roles;

    public User() {
    }

    public User(ObjectId id, String userName, String password, List<JournalEntry> journalList, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.journalList = journalList;
        this.roles = roles;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<JournalEntry> getJournalList() {
        return journalList;
    }

    public void setJournalList(List<JournalEntry> journalList) {
        this.journalList = journalList;
    }
}
