package com.example.squareplay;

public enum eDifficulty {
    EASY(0.2), MEDIUM(.5), HARD(.9);

    private final Double eDifficulty;

    private eDifficulty(Double gDifficulty) {
        this.eDifficulty = gDifficulty;
    }

    public Double geteDifficulty() {
        return eDifficulty;
    }

}
