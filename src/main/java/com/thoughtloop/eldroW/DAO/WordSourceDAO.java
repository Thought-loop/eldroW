package com.thoughtloop.eldroW.DAO;

public interface WordSourceDAO {


    //checks if word exists in the data source
    boolean isWordInDictionary(String guess);

    String getWord(int wordId);

    //return a new 5 letter word
    int getNewWord();


}
