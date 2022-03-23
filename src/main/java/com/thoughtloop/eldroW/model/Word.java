package com.thoughtloop.eldroW.model;

import com.thoughtloop.eldroW.DAO.WordSourceDAO;
import com.thoughtloop.eldroW.exception.InvalidWordFormatException;
import com.thoughtloop.eldroW.exception.NonDictonaryWordException;

public class Word {

    private String thisWord;

    public Word(String thisWord){
        this.thisWord = thisWord.toUpperCase();
    }

    public String getThisWord() {
        return thisWord;
    }

    public void setThisWord(String thisWord) {
        this.thisWord = thisWord.toUpperCase();
    }

    @Override
    public String toString() {
        return thisWord;
    }

    public static void validateWord(String word, WordSourceDAO wordSourceDAO) throws InvalidWordFormatException, NonDictonaryWordException {
        if((word.length() != 5) || (!word.matches("[a-zA-Z]+"))){
            throw new InvalidWordFormatException();
        }

        if(!wordSourceDAO.isWordValid(word)){
            throw new NonDictonaryWordException();
        }
    }
}
