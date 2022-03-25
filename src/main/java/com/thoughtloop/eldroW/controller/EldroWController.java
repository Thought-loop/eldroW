package com.thoughtloop.eldroW.controller;
import com.thoughtloop.eldroW.DAO.WordSourceDAO;
import com.thoughtloop.eldroW.model.Guess;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EldroWController {


    private WordSourceDAO wordSourceDAO;


    public EldroWController(WordSourceDAO wordSourceDAO) {
        this.wordSourceDAO = wordSourceDAO;
    }


    //checks word against solution
    //return array values: {0,0,0,0,0}
    //all -1 = invalid guess
    //0=letter not in solution
    //1=letter in solution, not in correct position
    //2=letter in correct position
    //3=letter in solution, but already placed in proper slot OR proper quantity already shown
    // (and no more of those characters remain)
    @RequestMapping(path = "/guess", method = RequestMethod.POST)
    public int[] checkGuess(@RequestBody Guess userGuess){
        System.out.println(userGuess);

        int solutionId = userGuess.getSolutionId();
        String guess = userGuess.getGuess().toLowerCase();


        int[] response = new int[]{-1,-1,-1,-1,-1};

        if (wordSourceDAO.isWordInDictionary(guess)) {
            String solution = wordSourceDAO.getWord(solutionId);

            //parse solution string into map where key=character and value = num of times char is in word
            Map<Character, Integer> solutionLetterCounts = new HashMap<>();
            char[] solutionChars = solution.toCharArray();
            solutionLetterCounts.put(solutionChars[0],1);
            for (int i = 1; i < solutionChars.length; i++) {
                if(solutionLetterCounts.containsKey(solutionChars[i]))  {
                    solutionLetterCounts.replace(solutionChars[i], solutionLetterCounts.get(solutionChars[i])+1);
                }
                else{
                    solutionLetterCounts.put(solutionChars[i],1);
                }
            }


            System.out.println(solutionLetterCounts);

            char[] guessChars = guess.toCharArray();
            for (int i = 0; i < 5; i++) {
                if (guessChars[i] == solution.charAt(i)) {
                    response[i] = 2;
                } else if (solution.contains(String.valueOf(guessChars[i]))) {
                    response[i] = 1;
                } else {
                    response[i] = 0;
                }
            }
            //after we do a first pass, need to first decrement correct character counts in map
            //then we check contains characters from left to right, setting response to 3 if zero counts left in map
            //if there are counts left, decrement the count and loop

            for (int i = 0; i < 5; i++) {
                if(response[i]==2){
                    solutionLetterCounts.replace(solutionChars[i], solutionLetterCounts.get(solutionChars[i])-1);
                }
            }
            for (int i = 0; i < 5; i++) {
                if(response[i]==1){
                    if(solutionLetterCounts.get(guessChars[i])==0){
                        response[i]=3;
                    }
//                    else{
//                        solutionLetterCounts.replace(guessChars[i], solutionLetterCounts.get(guessChars[i])-1);
//                    }
                }

            }
            System.out.println(solutionLetterCounts);
            System.out.println(Arrays.toString(response));
        }
        return response;
    }


    //retrieve a random word_id from the database
    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public int newWord(){
        return wordSourceDAO.getNewWord();
    }

}
