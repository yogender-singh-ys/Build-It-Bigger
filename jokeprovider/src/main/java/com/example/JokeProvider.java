package com.example;

public class JokeProvider {

    private static String joke = "A: I have the perfect son.\n" +
            "B: Does he smoke?\n" +
            "A: No, he doesn't.\n" +
            "B: Does he drink whiskey?\n" +
            "A: No, he doesn't.\n" +
            "B: Does he ever come home late?\n" +
            "A: No, he doesn't.\n" +
            "B: I guess you really do have the perfect son. How old is he?\n" +
            "A: He will be six months old next wednesday. ";

    public JokeProvider() {

    }

    public String getJoke()
    {
        return joke;
    }
}
