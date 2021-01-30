package com.example.squareplay;

import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GestureDetectorCompat;

public class SquarePlay extends MainActivity {



    private int currentSwaps;
    private int score;
    private int currentGoal;

    public SquarePlay() throws ClassNotFoundException, NoSuchMethodException {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getCurrentSwaps() {
        return currentSwaps;
    }

    public void setCurrentSwaps(int currentSwaps) {
        this.currentSwaps = currentSwaps;
    }


    public int getCurrentGoal() {
        return currentGoal;
    }

    public void setCurrentGoal(int currentGoal) {
        this.currentGoal = currentGoal;
    }




/*    float x1, x2, y1, y2, dx, dy;

    public void onSwipeEvent(MotionEvent event){
    switch(event.getAction()) {
        case(MotionEvent.ACTION_DOWN):
            x1 = event.getX();
            y1 = event.getY();
            break;

        case(MotionEvent.ACTION_UP): {
            x2 = event.getX();
            y2 = event.getY();
            dx = x2-x1;
            dy = y2-y1;

            if(Math.abs(dx) > Math.abs(dy)) {
                if(dx>0)
                    onSwipeRight();
                else
                    onSwipeLeft();
            } else {
                if(dy>0)
                    onSwipeDown();
                else
                    onSwipeUp();
            }
        }
    }
}




    private void onSwipeLeft() {
        System.exit(0);
    }

    private void onSwipeRight() {
        System.exit(0);
    }

    private void onSwipeUp() {
        System.exit(0);
    }

    private void onSwipeDown() {
        System.exit(0);
    }

    private void makeMove(Cell c1, Cell c2) {

    }*/
}



