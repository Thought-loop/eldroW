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

    public char[] getChars(){
        return thisWord.toCharArray();
    }

    public void setThisWord(String thisWord) {
        this.thisWord = thisWord.toUpperCase();
    }

    @Override
    public String toString() {
        return thisWord;
    }

    public static void validateWord(Word word, WordSourceDAO wordSourceDAO) throws InvalidWordFormatException, NonDictonaryWordException {
        if((word.getThisWord().length() != 5) || (!word.getThisWord().matches("[a-zA-Z]+"))){
            throw new InvalidWordFormatException();
        }

        if(!wordSourceDAO.isWordInDictionary(word)){
            throw new NonDictonaryWordException();
        }
    }

    public boolean equals(Word word){
        if(thisWord.equals(word.toString())){
            return true;
        }
        return false;
    }
}
