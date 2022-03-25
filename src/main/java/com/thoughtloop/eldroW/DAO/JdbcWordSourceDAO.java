package com.thoughtloop.eldroW.DAO;

import com.thoughtloop.eldroW.model.User;
import com.thoughtloop.eldroW.model.Word;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcWordSourceDAO implements WordSourceDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcWordSourceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //check if a given word exists in either solutions_5 or valid_guesses_5 tables
    @Override
    public boolean isWordInDictionary(String guess) {
        String sql = "SELECT word FROM solutions_5 WHERE word = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, guess);
        if(results.next()){
            return true;
        }
        return false;
    }


    //checks word against solution
    //return array values: {0,0,0,0,0}
    //0=letter not in solution
    //1=letter in solution, not in correct position
    //2=letter in correct position


    @Override
    public String getWord(int wordId) {

        String sql = "SELECT word FROM solutions_5 WHERE solution IS true AND word_id = ?;";
        String solution = jdbcTemplate.queryForObject(sql, String.class, wordId);
        return solution;

    }


    //2315 words in solutions_5 table
    //query for a new word using randomly seeded word_id
    //check if word has already been played by user, query again if so
    @Override
    public int getNewWord() {

        int randomWordId = (int) (Math.random()*2316);
        String sql = "SELECT word FROM solutions_5 WHERE solution IS true AND word_id = ?;";
        System.out.println(jdbcTemplate.queryForObject(sql, String.class, randomWordId));

        return randomWordId;
    }

//    @Override
//    public User getOrCreateUser(int cookieID) {
//        //check to see if user already exists
//        String sql = "SELECT user_id FROM eldrow_user WHERE user_id = ?;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cookieID);
//        //if user doesn't exist, create and retrieve ID
//        if(!results.next()){
//            sql = "INSERT into eldrow_user (wins,losses,games) VALUES (0,0,0) RETURNING user_id;";
//            Integer id = jdbcTemplate.queryForObject(sql,Integer.class);
//        }
//        //map new/existing user to object and return
//
//    }
//
//    private User mapRowToUser(SqlRowSet rs){
//        User user = new User();
//
//    }


}
