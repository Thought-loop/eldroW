package com.thoughtloop.eldroW.DAO;

import com.thoughtloop.eldroW.model.Word;

import java.util.List;

public interface WordSourceDAO {


    //checks if word exists in the data source
    boolean isWordValid(String word);

    //return a new 5 letter word that was not on the list previousWords
    Word getNewWord(List<Word> previousWords);


}
