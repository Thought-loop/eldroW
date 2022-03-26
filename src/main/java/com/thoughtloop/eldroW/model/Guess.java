package com.thoughtloop.eldroW.model;

import java.util.Arrays;

public class Guess {
    private String guess;
    private int solutionId;
    private String[] correctChars;
    private String[] containsChars;

    public Guess(String guess, int solutionId, String[] correctChars, String[] containsChars) {
        this.guess = guess;
        this.solutionId = solutionId;
        this.correctChars = correctChars;
        this.containsChars = containsChars;
    }

    public Guess(int solutionId) {
        this.solutionId = solutionId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public String[] getCorrectChars() {
        return correctChars;
    }

    public void setCorrectChars(String[] correctChars) {
        this.correctChars = correctChars;
    }

    public String[] getContainsChars() {
        return containsChars;
    }

    public void setContainsChars(String[] containsChars) {
        this.containsChars = containsChars;
    }

    @Override
    public String toString() {
        return "Guess{" +
                "guess='" + guess + '\'' +
                ", solutionId=" + solutionId +
                ", correctChars=" + Arrays.toString(correctChars) +
                ", containsChars=" + Arrays.toString(containsChars) +
                '}';
    }
}
