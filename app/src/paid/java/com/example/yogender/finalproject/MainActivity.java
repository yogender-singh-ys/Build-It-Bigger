package com.example.yogender.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yogender.jokeviewer.JokeDisplayActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity  implements OnJokeLoad {


    private Button btnFullscreenAd;
    private String intentText;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFullscreenAd = (Button) findViewById(R.id.btn_fullscreen_ad);
        btnFullscreenAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullJoke();
            }
        });

    }


    public void pullJoke()
    {
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(this).execute(getString(R.string.live_server));
    }

    @Override
    public void jokeLoadTaskHandler(String intentText) {

        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.EXTRA_MESSAGE, intentText);
        startActivity(intent);

    }

}
