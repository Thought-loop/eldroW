package com.thoughtloop.eldroW.model;

public class Record {

    private int wins;
    private int[] numOfGuesses = new int[6];
    private int losses;
    private int incompletes;

    public Record() {
        wins = 0;
        losses = 0;
        incompletes = 0;
    }

    public Record(int wins, int[] numOfGuesses, int losses, int incompletes) {
        this.wins = wins;
        this.numOfGuesses = numOfGuesses;
        this.losses = losses;
        this.incompletes = incompletes;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int[] getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses(int[] numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getIncompletes() {
        return incompletes;
    }

    public void setIncompletes(int incompletes) {
        this.incompletes = incompletes;
    }
}
