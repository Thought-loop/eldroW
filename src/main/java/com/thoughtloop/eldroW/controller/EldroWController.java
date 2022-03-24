package com.thoughtloop.eldroW.controller;
import com.thoughtloop.eldroW.DAO.WordSourceDAO;
import org.springframework.web.bind.annotation.*;

@RestController
public class EldroWController {


    private WordSourceDAO wordSourceDAO;


    public EldroWController(WordSourceDAO wordSourceDAO) {
        this.wordSourceDAO = wordSourceDAO;
    }
}
