package com.nemo.footballassociation.Contracts.Modules.DbModels;

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
