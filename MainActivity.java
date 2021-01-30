package com.example.squareplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements  GestureDetector.OnGestureListener{

    private ArrayList<Cell> allCells = new java.util.ArrayList<Cell>();
    private ArrayList<ImageView> allImgView = new java.util.ArrayList<ImageView>();
    private static final int SWIPE_DISTANCE_THRESHOLD = 25;
    private static final int SWIPE_VELOCITY_THRESHOLD = 25;
    private boolean bIntSwitch = false;
    //private boolean bMode = true; // true - addition, false - subtraction
    private int currentSwaps;
    private int currentShuffles;
    private int score;
    private int currentGoal;
    private int currentProgress;
    private SecureRandom random = new SecureRandom();
    private static eDifficulty difficulty = eDifficulty.MEDIUM;
    private int[] gamepiece = {
            R.drawable.one,
            R.drawable.two1,
            R.drawable.three1,
            R.drawable.four1,
            R.drawable.five1,
            R.drawable.six1,
            R.drawable.seven1,
            R.drawable.eight1,
            R.drawable.nine1,
            R.drawable.ten1,
            R.drawable.eleven1,
            R.drawable.twelve1,
            R.drawable.thirteen1,
            R.drawable.fourteen1,
            R.drawable.fifteen1,
            R.drawable.sixteen1,
            R.drawable.seventeen1,
            R.drawable.eighteen1,
            R.drawable.nineteen1,
            R.drawable.twenty1,
            R.drawable.twentyone,
            R.drawable.twenty2,
            R.drawable.twenty3,
            R.drawable.twenty4,
            R.drawable.twenty5,
            R.drawable.twenty6,
            R.drawable.twenty7,
            R.drawable.twenty8,
            R.drawable.twenty9,
            R.drawable.thirty,
            R.drawable.thirty1,
            R.drawable.thirty2,
            R.drawable.thirty3,
            R.drawable.thirty4,
            R.drawable.thirty5,
            R.drawable.thirty6,
            R.drawable.thirty7,
            R.drawable.thirty8,
            R.drawable.thirty9,
            R.drawable.forty,
            R.drawable.forty1,
            R.drawable.forty2,
            R.drawable.forty3,
            R.drawable.forty4,
            R.drawable.forty5,
            R.drawable.forty6,
            R.drawable.forty7,
            R.drawable.forty8,
            R.drawable.forty9,
            R.drawable.fifty,
            R.drawable.fifty1,
            R.drawable.fifty2,
            R.drawable.fifty3,
            R.drawable.fifty4,
            R.drawable.fifty5,
            R.drawable.fifty6,
            R.drawable.fifty7,
            R.drawable.fifty8,
            R.drawable.fifty9,
            R.drawable.sixty,
            R.drawable.sixty1,
            R.drawable.sixty2,
            R.drawable.sixty3,
            R.drawable.sixty4,
            R.drawable.sixty5,
            R.drawable.sixty6,
            R.drawable.sixty7,
            R.drawable.sixty8,
            R.drawable.sixty9,
            R.drawable.seventy,
            R.drawable.seventy1,
            R.drawable.seventy2,
            R.drawable.seventy3,
            R.drawable.seventy4,
            R.drawable.seventy5,
            R.drawable.seventy6,
            R.drawable.seventy7,
            R.drawable.seventy8,
            R.drawable.seventy9,
            R.drawable.eighty

    };

    private int[] swapPic = {
            R.drawable.pinkc2
    };
    private int currNumOfSwaps = 3;
    private int iDcnt = 1;
    private GestureDetector gestureDetector;
    private Cell currentCell;
    private int widthOfSboard;
    private int widthOfSblock;


    public void setCurrentShuffles(int i){
        currentShuffles = i;
    }

    public int getCurrentShuffles(){
        return currentShuffles;
    }
    /**
     *
     * @returns current swaps
     */
    public int getCurrentSwaps() {
        return currentSwaps;
    }

    /**
     *
     * sets difficulty based on seek bar progress
     */
    public void setDifficulty(int i) {
        if(i==0){
            difficulty = eDifficulty.EASY;
        }
        else if(i==1){
            difficulty = eDifficulty.MEDIUM;
        }
        else{
            difficulty = eDifficulty.HARD;
        }

    }

    public eDifficulty getDifficulty(){
        return difficulty;
    }


    /**
     *
     * @param currentSwaps      sets current swaps to given value
     */
    public void setCurrentSwaps(int currentSwaps) {
        this.currentSwaps = currentSwaps;
    }

    /**
     *
     * @return current score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score     sets local score to given variable
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return current goal variable
     */
    public int getCurrentGoal() {
        return currentGoal;
    }

    /**
     *
     * @param currentGoal       changed variable to given int
     */
    public void setCurrentGoal(int currentGoal) {
        this.currentGoal = currentGoal;
    }

    /**
     *
     * @return  current progress
     */
    public int getCurrentProgress() {
        return currentProgress;
    }

    /**
     *
     * @param currentProgress       sets local currentProgress to given int
     */
    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    /**
     *
     * @return current cell detected by Gesture detector
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     *
     * @param currentCell       sets currentCell variable to given Cell
     */
    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    /**
     *
     * @return swapPic int [], containing both pic IDs
     */
    public int[] getSwapPic() {
        return swapPic;
    }

    /**
     *
     * @return  int [] containing iDs for all gamepiece images
     */
    public int[] getGamepiece() {
        return gamepiece;
    }

    /**
     *
     * @return  current number of swaps available for the player
     */
    public int getCurrNumOfSwaps() {
        return currNumOfSwaps;
    }

    /**
     *
     * @param currNumOfSwaps        sets value of currNumOfSwaps to given int
     */
    public void setCurrNumOfSwaps(int currNumOfSwaps) {
        this.currNumOfSwaps = currNumOfSwaps;
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
    }*/

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout lay = (ConstraintLayout) findViewById(R.id.mainLayout);
        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView goalLbl = (TextView) findViewById(R.id.goalLbl);
        goalLbl.setEnabled(false);
        TextView scoreLbl = (TextView) findViewById(R.id.scoreLbl);
        scoreLbl.setEnabled(false);
        TextView swapLbl = (TextView) findViewById(R.id.swapLbl);
        swapLbl.setEnabled(false);
        TextView curgoalLbl = (TextView) findViewById(R.id.curgoalLbl);
        curgoalLbl.setEnabled(false);
        curgoalLbl.setText("5");
        setCurrentGoal(5);
        TextView currShuffles = (TextView) findViewById(R.id.numShuffles);
        currShuffles.setEnabled(false);
        currShuffles.setText("3");
        setCurrentShuffles(3);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        setCurrentProgress(0);
        TextView mode = (TextView) findViewById(R.id.difficultyDisp);
        mode.setEnabled(false);
        mode.setText(String.valueOf(getDifficulty()));

        ImageView pauseB = (ImageView) findViewById(R.id.pausePic);
        pauseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseGame();
            }
        });

        ImageView shuffleB = (ImageView) findViewById(R.id.shuffle);
        shuffleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentShuffles>1) {
                    shuffleCells();
                    setCurrentShuffles(currentShuffles - 1);
                    currShuffles.setText(String.valueOf(currentShuffles));
                }
                else{
                    shuffleCells();
                    shuffleB.setVisibility(View.INVISIBLE);
                    currShuffles.setVisibility(View.INVISIBLE);
                }
            }
        });

        TextView curscoreLbl = (TextView) findViewById(R.id.curscoreLbl);
        curscoreLbl.setEnabled(false);
        curscoreLbl.setText("0");
        setScore(0);


        //gestureDetector = new GestureDetectorCompat(this, this);

        //Controls Board size
        int noOfBlocks = 6;
        DisplayMetrics metricDisplay = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metricDisplay);
        int widthOfBoard = (int) (metricDisplay.widthPixels * .94);
        int widthOfBlock = widthOfBoard / noOfBlocks;


        GridLayout gameBoard = findViewById(R.id.board);
        gameBoard.setRowCount(noOfBlocks);
        gameBoard.setColumnCount(noOfBlocks);
        gameBoard.getLayoutParams().width = widthOfBoard;
        gameBoard.getLayoutParams().height = widthOfBoard;


        int iDCnt = 1;

        final GestureDetector gdt = new GestureDetector(new GestureListener());
        for (int i = 0; i < noOfBlocks; i++) {
            for (int j = 0; j < noOfBlocks; j++) {
                ImageView imageView = new ImageView(this);
                imageView.setId(iDcnt);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
                imageView.setMaxHeight(widthOfBlock);
                imageView.setMaxWidth(widthOfBlock);
                int randomNum = (int) Math.floor(random.nextDouble() * 4);
                imageView.setImageResource(gamepiece[randomNum]);
                Cell c = new Cell(i + 1, j + 1, randomNum+1, imageView.getId());
                allCells.add(c);
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    //ImageView v1 = imageView;
                    @Override
                    public boolean onTouch(final View view, final MotionEvent event) {
                        gdt.onTouchEvent(event);
                        currentCell = findCell(view.getId());
                        return true;
                    }
                });

                allImgView.add(imageView);
                gameBoard.addView(imageView);
                iDcnt++;
            }



        }
        //Initalize number of swaps
        int initNumOfSwaps = 4;
        /*GridLayout swapBoard = findViewById(R.id.swapDisp);
        swapBoard.setColumnCount(initNumOfSwaps);
        swapBoard.setRowCount(1);*/
        widthOfSboard = (int) (metricDisplay.widthPixels * .58);
        widthOfSblock = widthOfSboard / initNumOfSwaps;

        displaySwaps(initNumOfSwaps);
        setCurrentSwaps(initNumOfSwaps);


    }

    private void displaySwaps(int nSwaps){
        GridLayout swapBoard = findViewById(R.id.swapDisp);
        swapBoard.removeAllViews();
        swapBoard.setColumnCount(nSwaps);
        swapBoard.setRowCount(1);
        for (int i = 0; i < nSwaps; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfSblock, widthOfSblock));
            imageView.setMaxHeight(widthOfSblock);
            imageView.setMaxWidth(widthOfSblock);
            imageView.setImageResource(swapPic[0]);
            swapBoard.addView(imageView);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean validateGame(Cell current, Cell next){
        ProgressBar pg = (ProgressBar) findViewById(R.id.progressBar);
        TextView goalLabel = (TextView) findViewById(R.id.curgoalLbl);
        TextView scoreLabel = (TextView) findViewById(R.id.curscoreLbl);
        if(findNextValue(current, next) == getCurrentGoal()) {
            if (getCurrentProgress() == 66) {
                setCurrentProgress(100);
                pg.setProgress(getCurrentProgress());
                setCurrentProgress(0);
                pg.setProgress(0);
                findNextAdditionGoal();
                //setCurrentGoal(getCurrentGoal() + 1);
                goalLabel.setText(String.valueOf(getCurrentGoal()));
                setScore(getScore() + 200);
                scoreLabel.setText(String.valueOf(getScore()));
                setCurrentSwaps(4);
                displaySwaps(getCurrentSwaps());


            }
            else {
                setCurrentProgress(getCurrentProgress() + 33);
                setScore(getScore() + 100);
                pg.setProgress(getCurrentProgress());
                scoreLabel.setText(String.valueOf(getScore()));
            }
        }
        else{
            if(getCurrentSwaps() != 0) {
                setCurrentSwaps(getCurrentSwaps() - 1);
                displaySwaps(getCurrentSwaps());
                return false;
            }
            else{
                endGame();
                return false;
            }
        }
        return true;
    /*if(currentGoal == 8){
        endGame();
    }*/

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void findNextAdditionGoal() {
        int sum = 0;
        for(Cell c: allCells){
            c.clearPossibleGoals();
        }

        for(Cell c1: allCells) {
            for (Cell c2 : allCells) {
                if (c1.getRow() == c2.getRow()) {
                    if (c1.getCol() == c2.getCol() + 1) {
                        sum = c2.getcValue() + c1.getcValue();
                        c1.addPossibleGoals(sum);
                    }
                }

                if (c1.getRow() == c2.getRow()+1) {
                    if (c1.getCol() == c2.getCol()) {
                        sum = c2.getcValue() + c1.getcValue();
                        c1.addPossibleGoals(sum);
                    }
                }
            }
        }

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for(Cell c1: allCells) {
            for(Integer i: c1.getPossibleGoals()){
                if(map.containsKey(i)){
                    map.put(i, map.get(i)+ 1);
                }
                else{
                    map.put(i, 1);

                }
            }
        }
        LinkedHashMap<Integer, Integer> finalGoals = new LinkedHashMap<Integer, Integer>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x-> finalGoals.put(x.getKey(), x.getValue()));
        /*finalGoals.entrySet().stream().filter(c -> c.getValue() > 3);
        finalGoals.entrySet().stream().filter(c -> c.getKey() <= 11);
        finalGoals.remove(currentGoal);*/


        /*Iterator<Integer> it = finalGoals.keySet().iterator();
        while (it.hasNext()) {
            int i = it.next();
            if (i > 81 && i <=3) {
                finalGoals.remove(i);
            }
        }*/
        /*Iterator<Integer> it2 = finalGoals.values().stream().filter(c -> c > 3).iterator();
        while(it2.hasNext()){
            int i= it2.next();
            if(i<=3){
                finalGoals.
            }
        }*/

        LinkedHashMap<Integer,Integer> filteredGoals = new LinkedHashMap<Integer,Integer>();   //combine map and finalgoals, expand map stream or allow for map to be apart of for loop

        for(LinkedHashMap.Entry<Integer,Integer> entry : finalGoals.entrySet()){
            if(entry.getValue() > 3 && (entry.getKey() != currentGoal) && (entry.getKey() >= 4 && entry.getKey() < 81)){
                filteredGoals.put(entry.getKey(),entry.getValue());
            }
            /*else if(entry.getValue() != currentGoal){
                continue;
            }
            else if(entry.getKey() < 4 || entry.getKey() > 81){
                continue;
            }
            else{
                continue;
            }*/

        }

        int goalIndex = (int) (filteredGoals.size()*difficulty.geteDifficulty());// CHANGE TO VALUE OF ENUM RELATED TO DIFFICULTY
        List<Integer> listed = new ArrayList<Integer>(filteredGoals.keySet().stream().collect(Collectors.toList()));
        //List<Integer> finalList = new ArrayList<Integer>(listed.addAll(j -> finalList.get));
        int minNum = listed.size() - goalIndex;
        int randomIndex = 0;
        if(difficulty.geteDifficulty().equals(eDifficulty.EASY)) {

            randomIndex = (minNum * random.nextInt() * listed.size()) + goalIndex;
        }
        if(difficulty.geteDifficulty().equals(eDifficulty.MEDIUM)){
            int maxIndex = (int) (filteredGoals.size()*eDifficulty.EASY.geteDifficulty());
            randomIndex = minNum + (random.nextInt() * (maxIndex-minNum));

        }
        if(difficulty.geteDifficulty().equals(eDifficulty.HARD)){
            int maxIndex = (int) (filteredGoals.size()*eDifficulty.MEDIUM.geteDifficulty());
            randomIndex = minNum + (random.nextInt() * (maxIndex-minNum));
        }

        setCurrentGoal(listed.get(randomIndex));
        TextView goal = (TextView) findViewById(R.id.curgoalLbl);
        goal.setText(String.valueOf(currentGoal));



//ENUM created addtion Logic finished.



    }

    private void pauseGame(){
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.pause_popup, null);
        int iWidth = (int)(width*(0.66));
        int iHeight = height/2;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(layout, iWidth, iHeight, focusable);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 1, 1);
        TextView pauseM = (TextView) layout.findViewById(R.id.pause_text);
        pauseM.setEnabled(false);

        HomeScreen home = new HomeScreen();
        home.setiHighScore(score);

        Button resumeB = (Button) layout.findViewById(R.id.resume_button);
        resumeB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Close Window
                popupWindow.dismiss();
            }
        });
        Button restartB = (Button) layout.findViewById(R.id.restart_button);
        restartB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do Something
                startActivity(new Intent(MainActivity.this, HomeScreen.class));
                //Close Window
                popupWindow.dismiss();
            }
        });


    }


    private void endGame(){
        //shows popup window with restart option
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.end_popup, null);

        // Creating the PopupWindow
        int iWidth = (int)(width*(0.66));
        int iHeight = height/2;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(layout, iWidth, iHeight, focusable);

        // prevent clickable background
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 1, 1);


        TextView endM = (TextView) layout.findViewById(R.id.endGame_message);
        TextView scoreM = (TextView) layout.findViewById(R.id.endGame_score1);
        scoreM.setText("Score: " + String.valueOf(score));

        HomeScreen home = new HomeScreen();
        home.setiHighScore(score);
        //scoreM.setTextColor(255);

        // Getting a reference to button one and do something
        Button restartB = (Button) layout.findViewById(R.id.restart_button);
        restartB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do Something
                refreshBoard();
                //Close Window
                popupWindow.dismiss();
            }
        });
    }


    private void refreshBoard(){
        //to be implemented at end of game, generates new board
        finish();
        startActivity(getIntent());
    }

    private boolean swap(Cell currentC, Cell nextC){
        //swaps two cells without incrementing values
        //returns true if swap is made
        //int tempID = nextC.getImgID();
        int tempV = nextC.getcValue();
        for (ImageView i : allImgView) {
            if (nextC.getImgID() == i.getId()) {
                i.setImageResource(gamepiece[currentC.getcValue() - 1]);
                nextC.setCellValue(currentC.getcValue());
            }
        }
        for(ImageView i : allImgView){
            if(currentC.getImgID() == i.getId()){
                i.setImageResource(gamepiece[tempV-1]);
                currentC.setCellValue(tempV);
            }
        }
        return true;
    }

    /**
     *
     * @param c     the current cell, set to be changed to new int value
     */
    /*private void spawnNewInt(Cell c){
        if(bIntSwitch == false) {
            c.setCellValue(1);
        }
        else {
            c.setCellValue(2);
        }
        bIntSwitch = !bIntSwitch;
        setImage(c, c.getcValue());

    }*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void spawnNewInt(Cell c) {
        /**
         * switches current cell value and image to 1 or 2, alternating based on bIntSwitch
         */
        //Delete bIntSwitch
        ArraySet<Integer> valRange = new ArraySet<Integer>();
        for (Cell cell : allCells) {
            valRange.add(cell.getcValue());
        }
        valRange.removeIf(i ->valRange.indexOf(i) >= valRange.size()/2);
        valRange.iterator()
                .forEachRemaining(i -> {
                    if (valRange.indexOf(i) == (int) (valRange.size() * random.nextDouble())) {
                        c.setCellValue(i);
                    }
                });

        setImage(c, c.getcValue());
        //int r = (int)((random.nextDouble() *5) + 1);
        //setImage(c, r);
        // c.setCellValue(random.nextDouble()*); 
    }

    private void setImage(Cell nextCell) {
        //if(nextCell.getcValue()!= findNextValue(currentCell, nextCell)) {
            for (ImageView i : allImgView) {
                if (nextCell.getImgID() == i.getId()) {
                    i.setImageResource(gamepiece[findNextValue(currentCell, nextCell) - 1]);
                    nextCell.setCellValue(findNextValue(currentCell, nextCell));
                    break;
                }
            }
        //}
    }
    private void setImage(Cell nextCell, int random) {
        //if(nextCell.getcValue()!= findNextValue(currentCell, nextCell)) {
            for (ImageView i : allImgView) {
                if (nextCell.getImgID() == i.getId()) {
                    i.setImageResource(gamepiece[random - 1]);
                    break;
                }
            }
        //}
    }

    private void setImage(){
        for (ImageView i : allImgView) {
            for(Cell c: allCells) {
                if (c.getImgID() == i.getId()) {
                    i.setImageResource(gamepiece[c.getcValue() -1]);
                    break;
                }
            }
        }
    }

    private void shuffleCells(){
        ArrayList<Integer> currentCellValues = new ArrayList<Integer>();
        int iCnt =0;
        for(Cell c: allCells){
            currentCellValues.add(c.getcValue());
        }
        Collections.shuffle(currentCellValues);
        for(Cell c: allCells){
            c.setCellValue(currentCellValues.get(iCnt));
            iCnt++;
        }
        setImage();


    }



    private Cell findCell(int Id){
        for(Cell c: allCells){
            if(Id == c.getImgID()){
                return c;
            }

        }
        return null;


    }

    private int findNextValue(Cell curr, Cell next){
        //if(bMode == true) {
            return (curr.getcValue() + next.getcValue());
        /*
        else{

            if((next.getcValue() - curr.getcValue())<=0){
                return next.getcValue();
            }
            return (next.getcValue() - curr.getcValue());
        }*/

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onFling(MotionEvent dEvent, MotionEvent uEvent, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = uEvent.getY() - dEvent.getY();
            float diffX = uEvent.getX() - dEvent.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {

                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                    result = true;
                }
            } else if (Math.abs(diffY) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSwipeRight() {
        Cell nextCell = null;
        for(Cell c: allCells){
            if(c.getCol() == currentCell.getCol()+1){
                if(c.getRow() == currentCell.getRow()){
                    nextCell = c;
                    break;
                }
            }
        }
        if(validateGame(currentCell, nextCell)==false){
            swap(currentCell, nextCell);
            return;
        }
        setImage(nextCell);
        spawnNewInt(currentCell);

        Toast.makeText(getBaseContext(), "Right", Toast.LENGTH_SHORT).show();//up
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSwipeLeft() {
        Cell nextCell = null;
        for(Cell c: allCells){
            if(c.getCol() == currentCell.getCol()-1){
                if(c.getRow() == currentCell.getRow()){
                    nextCell = c;
                    break;
                }
            }
        }
        if(validateGame(currentCell, nextCell)==false){
            swap(currentCell, nextCell);
            return;
        }
        setImage(nextCell);
        spawnNewInt(currentCell);


        Toast.makeText(getBaseContext(), "Left", Toast.LENGTH_SHORT).show();//up
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSwipeBottom() {
        Cell nextCell = null;
        for(Cell c: allCells){
            if(c.getCol() == currentCell.getCol()){
                if(c.getRow() == currentCell.getRow()+1){
                    nextCell = c;
                    break;
                }
            }
        }

        if(validateGame(currentCell, nextCell)==false){
            swap(currentCell, nextCell);
            return;
        }
        setImage(nextCell);
        spawnNewInt(currentCell);

        Toast.makeText(getBaseContext(), "Bottom", Toast.LENGTH_SHORT).show();//up
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onSwipeTop() {
        Cell nextCell = null;
        for(Cell c: allCells){
            if(c.getCol() == currentCell.getCol()){
                if(c.getRow() == currentCell.getRow()-1){
                    nextCell = c;
                    break;
                }
            }
        }

        if(validateGame(currentCell, nextCell)==false){
            swap(currentCell, nextCell);
            return;
        }
        setImage(nextCell);
        spawnNewInt(currentCell);


        Toast.makeText(getBaseContext(), "Top", Toast.LENGTH_SHORT).show();//up
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {


        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }




}




















