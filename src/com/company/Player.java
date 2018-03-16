package com.company;

/*
Use Classes to remove repetitive parts of code and abstract out a “Player” class used to manage the state of the
        player (if they have won or loss), how many points they have, what move they have made
*/

public class Player {

    private boolean isHuman;
    private int oneOrTwo;
    private String wOrL;
    private int points;
    private String move;

    public Player(boolean isHuman, int oneOrTwo) {
        this.isHuman = isHuman;
        this.oneOrTwo = oneOrTwo;
        this.wOrL = "";
        this.points = 0;
        this.move = "";
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public int getOneOrTwo() {
        return oneOrTwo;
    }

    public void setOneOrTwo(int oneOrTwo) {
        this.oneOrTwo = oneOrTwo;
    }

    public String getwOrL() {
        return wOrL;
    }

    public void setwOrL(String wOrL) {
        this.wOrL = wOrL;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
