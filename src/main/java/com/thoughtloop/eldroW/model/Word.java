package com.thoughtloop.eldroW.model;

import com.thoughtloop.eldroW.exception.InvalidWordException;

public class Word {

    private String thisWord;

    public Word(String thisWord){
        try {
            Word.validateWord(thisWord);
            this.thisWord = thisWord;
        } catch (InvalidWordException e) {
            e.printStackTrace();
            this.thisWord = "XXXXX";
        }

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

    public static void validateWord(String word) throws InvalidWordException{
        if((word.length() != 5) || (!word.matches("[a-zA-Z]+"))){
            throw new InvalidWordException();
        }
    }
}
