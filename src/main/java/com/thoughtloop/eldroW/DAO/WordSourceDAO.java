package com.thoughtloop.eldroW.DAO;

import com.thoughtloop.eldroW.model.User;
import com.thoughtloop.eldroW.model.Word;

import java.util.List;

public interface WordSourceDAO {


    //checks if word exists in the data source
    boolean isWordInDictionary(String guess);

    String getWord(int wordId);

    //return a new 5 letter word that was not on the list previousWords
    int getNewWord();



//    User getOrCreateUser(int cookieID);
}
