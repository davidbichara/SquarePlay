package com.example.squareplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    private static int iHighScore;

    public int getiHighScore(){
        return iHighScore;
    }

    public void setiHighScore(int i){
        iHighScore = i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        MainActivity main = new MainActivity();
        HowToPlay htp = new HowToPlay();
        //creates title
        ImageView title  = (ImageView)  findViewById(R.id.title);

        SharedPreferences prefs = this.getSharedPreferences("highScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("key", iHighScore);
        editor.commit();

        TextView highScore = (TextView) findViewById(R.id.HighScore);
        highScore.setText("High Score: "+ String.valueOf(iHighScore));


        Button gameStart = (Button) findViewById(R.id.startButton);
        gameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, MainActivity.class));
            }
        });

        Button howToPlay = (Button) findViewById(R.id.howToPlayButton);
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this, HowToPlay.class));
            }
        });

        SeekBar difficulty = (SeekBar) findViewById(R.id.seekBar);
        int mode = difficulty.getProgress();
        main.setDifficulty(mode - 1);
        TextView currentDiff = (TextView) findViewById(R.id.currDifficulty);
        currentDiff.setText(String.valueOf(main.getDifficulty()));
        currentDiff.setEnabled(false);
        difficulty.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch (SeekBar seekBar){
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch (SeekBar seekBar){
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
                // TODO Auto-generated method stub
                //int modeT = difficulty.getProgress();
                main.setDifficulty(progress - 1);
                TextView currentDiff = (TextView) findViewById(R.id.currDifficulty);
                currentDiff.setText(String.valueOf(main.getDifficulty()));
                currentDiff.setEnabled(false);
            }
        });


    }
}
