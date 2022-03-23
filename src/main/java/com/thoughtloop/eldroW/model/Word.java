package com.thoughtloop.eldroW.model;

import com.thoughtloop.eldroW.exception.InvalidWordException;

public class Word {

    private String thisWord;

    public Word(String thisWord) throws InvalidWordException {
        if((thisWord.length() != 5) || (!thisWord.matches("[a-zA-Z]+"))){
            throw new InvalidWordException();
        }
        this.thisWord = thisWord;
    }

    public String getThisWord() {
        return thisWord;
    }

    public void setThisWord(String thisWord) {
        this.thisWord = thisWord;
    }

    @Override
    public String toString() {
        return thisWord;
    }
}
