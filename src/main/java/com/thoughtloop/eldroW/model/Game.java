package com.thoughtloop.eldroW.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.ListUtils;

public class Game {
    private Word solution;
    private Word[] guessedWords;
    private List<Character> guessedChars;
    private int numGuessesMade;
    private User user;

    public Game(Word solution, User user) {
        this.solution = solution;
        this.user = user;
        this.guessedWords = new Word[6];
        this.numGuessesMade = 0;
        this.guessedChars = new ArrayList<>();
    }

    public Word getSolution() {
        return solution;
    }

    public void setSolution(Word solution) {
        this.solution = solution;
    }

    public Word[] getGuessedWords() {
        return guessedWords;
    }

    public Word[] addGuess(Word word) {
        if(numGuessesMade > 6){
            return null;
        }
        guessedWords[numGuessesMade] = word;
        guessedChars = ListUtils
        numGuessesMade++;
        return guessedWords;
    }

    public void setGuessedWords(Word[] guessedWords) {
        this.guessedWords = guessedWords;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
