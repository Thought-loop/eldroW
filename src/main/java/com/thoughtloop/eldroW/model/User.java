package com.thoughtloop.eldroW.model;

public class User {

    private String firstName;
    private String lastName;
    private long userID;
    private Record record;


    public User(String firstName, String lastName, long userID, Record record) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.record = record;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        long userID = -1;
        record = new Record();
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
