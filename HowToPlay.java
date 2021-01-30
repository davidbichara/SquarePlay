package com.example.squareplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HowToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);

        Button gameStart = (Button) findViewById(R.id.startButton1);
        gameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToPlay.this, MainActivity.class));
            }
        });

        Button toHome = (Button) findViewById(R.id.homeButton);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToPlay.this, HomeScreen.class));
            }
        });
    }
}
