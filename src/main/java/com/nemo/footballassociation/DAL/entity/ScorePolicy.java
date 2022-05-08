package com.nemo.footballassociation.DAL.entity;

public class ScorePolicy extends APolicy{
    private int pointsPerWin;
    private int pointsPerDraw;
    private int pointsPerLoss;

    public ScorePolicy(int pointsPerWin, int pointsPerDraw, int pointsPerLoss) {
        this.pointsPerWin = pointsPerWin;
        this.pointsPerDraw = pointsPerDraw;
        this.pointsPerLoss = pointsPerLoss;
    }
}
