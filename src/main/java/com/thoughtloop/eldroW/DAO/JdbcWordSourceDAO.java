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


    @Override
    public String getWord(int wordId) {
        String sql = "SELECT word FROM solutions_5 WHERE solution IS true AND word_id = ?;";
        String solution = jdbcTemplate.queryForObject(sql, String.class, wordId);
        return solution;
    }




    //2315 words in solutions_5 table
    //query for a new word using randomly seeded word_id
    //check if word has already been played by user, query again if so
    //max solution id range is 31460 - 33596
    @Override
    public int getNewWord() {
        int randomWordId = ((int)(Math.random()*(33596-31460)))+31459;
        System.out.println(randomWordId);
        String sql = "SELECT word FROM solutions_5 WHERE solution IS true AND word_id = ?;";
        System.out.println(jdbcTemplate.queryForObject(sql, String.class, randomWordId));
        return randomWordId;
    }
}
