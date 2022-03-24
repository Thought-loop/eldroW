package com.thoughtloop.eldroW.model;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private Word solution;
    private char[] solutionChars;
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
        this.solutionChars = solution.getChars();
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
        char[] wordChars = word.getChars();

        for (int i = 0; i < wordChars.length; i++) {
            if(!guessedChars.contains(wordChars[i])){
                guessedChars.add(wordChars[i]);
            }
        }
        numGuessesMade++;
        return guessedWords;
    }

    public boolean checkGuess(Word guess){
        if(solution.equals(guess)){
            return true;
        }
        return false;
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
