package com.example.MegaSenaAPI;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping(value="/megasena")
public class MegaSenaController {

    @GetMapping("/simpleMessageWelcome")
    public String welcomeMessage() {
        return "Welcome to the REST API for generating numbers for the Mega Sena lottery.";
    }

    @GetMapping("/getNumbers")
    public List<Integer> megaSenaNumbers() {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<Integer>();

        int NUMBER_COUNT = 6;
        int MIN_NUMBER = 0;
        int MAX_NUMBER = 999999;

        random.ints(NUMBER_COUNT, MIN_NUMBER, MAX_NUMBER).forEach(numbers::add);

        return numbers;
    }
}