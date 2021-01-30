package com.example.squareplay;

import java.io.Serializable;
import java.util.HashSet;

public class Cell implements Serializable {

    private int Col;
    private int Row;
    private int cValue;
    private int imgID;

    public Cell(int R, int C, int val, int imgID){
        this.Row = R;
        this.Col = C;
        this.cValue = val;
        this.imgID = imgID;
    }

    public int getRow() {
        return Row;
    }

    public int getCol() {
        return Col;
    }

    public int getcValue() {
        return cValue;
    }

    public void setCellValue(int val){
        this.cValue = val;
    }

    public int getImgID(){ return imgID; }

    public HashSet<Integer> getPossibleGoals() {
        return possibleGoals;
    }

    public void addPossibleGoals(Integer i) {
        possibleGoals.add(i);
    }

    private HashSet<Integer> possibleGoals = new HashSet<Integer>();

    public void clearPossibleGoals(){
        possibleGoals.clear();
    }


}
