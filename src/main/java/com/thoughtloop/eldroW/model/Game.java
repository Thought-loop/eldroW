package com.thoughtloop.eldroW.model;

public class Game {
    private Word solution;
    private Word[] guesses;
    private User user;

    public Game(Word solution, User user) {
        this.solution = solution;
        this.user = user;
    }

    public Word getSolution() {
        return solution;
    }

    public void setSolution(Word solution) {
        this.solution = solution;
    }

    public Word[] getGuesses() {
        return guesses;
    }

    public void setGuesses(Word[] guesses) {
        this.guesses = guesses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
