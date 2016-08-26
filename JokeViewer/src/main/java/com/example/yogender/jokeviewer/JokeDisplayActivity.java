package com.example.yogender.jokeviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private TextView jokeTextViewJDL;
    public final static String EXTRA_MESSAGE = "com.example.yogender.jokeviewer.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        // receive intent passed from main module
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        // displaying intent string in Textview
        jokeTextViewJDL = (TextView) findViewById(R.id.jokeTextViewJDL);
        jokeTextViewJDL.setText(message);

    }
}
