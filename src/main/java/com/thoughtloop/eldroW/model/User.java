package com.thoughtloop.eldroW.model;

import java.util.List;

public class User {

    private long userID;
    private Record record;
    private List<String> previousWords;


    public User(long userID, Record record) {
        this.userID = userID;
        this.record = record;
    }

    public User(long userID) {
        this.userID = userID;
    }

    public User() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
