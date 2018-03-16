package com.company;

/*
 “Player” class used to manage the state of the
 player (if they have won or loss), how many points they have, what move they have made
*/

public class Player {

    private boolean isHuman; //is it a human or computer player
    private int oneOrTwo; //is it player one or two
    private String wOrL; //win or loss record
    private int points; //number of points
    private String move; //what was last or current move

    public Player(boolean isHuman, int oneOrTwo) {
        this.isHuman = isHuman;
        this.oneOrTwo = oneOrTwo;
        this.wOrL = "";
        this.points = 0;
        this.move = "";
    }

    //below are simple getters and setters for above
    //

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
