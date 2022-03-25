package com.thoughtloop.eldroW.controller;
import com.thoughtloop.eldroW.DAO.WordSourceDAO;
import com.thoughtloop.eldroW.model.Guess;
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
            char[] solutionChars = solution.toCharArray();
            char[] containsChars = solution.toCharArray();
            char[] guessChars = guess.toCharArray();
            for (int i = 0; i < 5; i++) {
                if (guessChars[i] == solution.charAt(i)) {
                    response[i] = 2;
                    solutionChars[i] = ' ';
                    containsChars[i] = ' ';
                } else if (solution.contains(String.valueOf(guessChars[i]))) {
                    response[i] = 1;
                    containsChars[i] = ' ';
//                    solution = new String(containsChars);
                } else {
                    response[i] = 0;
                }
            }
            //guess    roost
            //solution frost
            //sol ch   frost
            //con ch     ost
            //response 11222
            for (int i = 0; i < 5; i++) {
                if(response[i]=='1'){
                    boolean repeatChar = false;
                    for(int j = 0; j<5; j++){
                        if(guessChars[i]==solutionChars[j]){
                            repeatChar = true;
                        }
                    }
                    if(!repeatChar){
                        response[i] = 3;
                    }
                }
            }
        }
        return response;
    }


    //retrieve a random word_id from the database
    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public int newWord(){
        return wordSourceDAO.getNewWord();
    }

}
