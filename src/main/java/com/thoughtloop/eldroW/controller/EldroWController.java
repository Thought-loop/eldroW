package com.thoughtloop.eldroW.controller;
import com.thoughtloop.eldroW.DAO.WordSourceDAO;
import com.thoughtloop.eldroW.model.Word;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(path = "/guess", method = RequestMethod.POST)
    public int[] checkGuess(String guess, int solutionId){
        int[] response = new int[]{-1,-1,-1,-1,-1};

        if (wordSourceDAO.isWordInDictionary(guess)) {
            String solution = wordSourceDAO.getWord(solutionId);

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
        }
        return response;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public int newWord(){
        return wordSourceDAO.getNewWord();
    }

}
