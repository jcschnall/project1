package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/*
To do
-test it more
-clean it up and done...................
*/


/*
-NOTE the multiThreading input for two players only works from the command line input!!!!!!!!!!!!!
-to run in command line:  javac src/com/osama/GHide/*.java
java -cp ./src com.osama.GHide.EnteringPoint

 */

/*
simple command line java rocks paper scissors game
works best through the command prompt for the password logging feature of the game
can be played with one player and a computer or two players
launches from main method, follow given instructions............
 */

public class Main {

    //game runs from main
    //
    public static void main(String[] args) {

        //vars for possible game combos, game history etc
        boolean quit = false;
        HashMap<String, String> combos = makeCombos();
        ArrayList<String> history = new ArrayList<>();
        String playerType = "";

        //print opening instructions
        printIntro();

        //read initial input with error checking
        Scanner s = new Scanner(System.in);
        String  gameMode = "";
        try {
            gameMode = s.next();
            playerType = gameMode;
            while(!(gameMode.equals("play_human") || gameMode.equals("play_computer") || gameMode.equals("history") || gameMode.equals("quit"))){
                printIntro();
                gameMode = s.next();
                playerType = gameMode;
            }

        }catch(Exception error){
            error.printStackTrace();
        }

        //while loop to run through various game modes and options until player quits.......
        while (!quit){

            if (gameMode.equals("play_human") || gameMode.equals("play_computer")) {
                play(combos, history, playerType);
            } else if (gameMode.equals("history")) {
                printHistory(history);
            } else {
                quit =true;
            }
            try {
                gameMode = s.next();
                playerType = gameMode;
                while(!(gameMode.equals("play_human") || gameMode.equals("play_computer") || gameMode.equals("history") || gameMode.equals("quit"))){
                    printIntro();
                    gameMode = s.next();
                    playerType = gameMode;
                }
                if (gameMode.equals("quit")) {
                    quit = true;
                    s.close();  //added here
                }
            }catch(Exception error){
                error.printStackTrace();
            }
        }
    }

    //create hashmap of possible game combos
    //
    private static HashMap<String, String> makeCombos(){

        HashMap<String, String> combos = new HashMap<>();
        combos.put("rock paper", "lose");
        combos.put("rock scissors", "win");
        combos.put("rock rock", "tie");
        combos.put("paper rock", "win");
        combos.put("paper scissors", "lose");
        combos.put("paper paper", "tie");
        combos.put("scissors rock", "lose");
        combos.put("scissors scissors", "tie");
        combos.put("scissors paper", "win");

        return combos;

    }

    //print intro
    //
    private static void printIntro(){
        System.out.println();
        System.out.println();
        System.out.println("Welcome to Rock Paper Scissors!");
        System.out.println("MAIN MENU");
        System.out.println("=====");
        System.out.println("1. Type \'play_computer\' or 'play_human' to play");
        System.out.println("2. Type \'history\' to view your game history");
        System.out.println("Type \'quit\' to stop playing");

    }

    //play the game either in 1 player or two player mode
    //
    private static void play(HashMap<String, String> combos, ArrayList<String> history, String playerType){
        //get player vs computer or human
        Scanner s = new Scanner(System.in);

        if(playerType.equals("play_computer")){
            //create player class instances
            Player player1 = new Player(true,1);
            Player player2 = new Player(false, 2);

            System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
            System.out.println("Type 'quit' to go back to the Main Menu");

            String answer1 = "";

            //error check input
            try {
                //computer adversary
                //
                answer1 = s.next();
                answer1 = answer1.toLowerCase();

                while(!(answer1.toLowerCase().equals("rock") || answer1.toLowerCase().equals("paper") || answer1.toLowerCase().equals("history") ||
                        answer1.equals("quit"))){
                    printAskForMove();
                    answer1 = s.next();
                }

                if(answer1.equals("quit")){
                    printIntro();
                    return;
                }

                //computer random input
                String answer2 = computerMove();

                traslateToHashandResults(answer1, answer2, combos, player1, player2);

                //print results and add to history
                System.out.println("Computer picks: " + answer2);
                System.out.println("User picks: " + answer1);

                String winOrLose = player1.getwOrL();
                System.out.println("you " + winOrLose +"!");

                addToHistory(history, player1, player2);
            }catch(Exception error){
                error.printStackTrace();
            }

        }else{
            //human adversary, not computer
            //

            //create player class instances
            Player player1 = new Player(true,1);
            Player player2 = new Player(true, 2);

            System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
            System.out.println("Type 'quit' to go back to the Main Menu");

            String answer1 = "";

            //use EraserThread and PasswordField classes to hide the input for this two player game
            try {
                //answer1 = s.next();

                //player1 input
                PasswordField getMove = new PasswordField();
                answer1 = getMove.readPassword("enter    ");
                answer1= answer1.toLowerCase();

                //error checking
                while(!(answer1.toLowerCase().equals("rock") || answer1.toLowerCase().equals("paper") || answer1.toLowerCase().equals("history") ||
                        answer1.equals("quit"))){

                    printAskForMove();
                    answer1 = s.next();
                }

                if(answer1.equals("quit")){
                    printIntro();
                    return;
                }

                System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
                System.out.println("Type 'quit' to go back to the Main Menu");

                String answer2 = "";
                try {
                    //answer2 = s.next();

                    //player2 input
                    getMove = new PasswordField();
                    answer2 = getMove.readPassword("enter    ");
                    answer2 = answer2.toLowerCase();

                    //error checking input
                    while(!(answer2.toLowerCase().equals("rock") || answer2.toLowerCase().equals("paper") || answer2.toLowerCase().equals("history") ||
                        answer2.equals("quit"))){
                        printAskForMove();
                        answer2 = s.next();
                    }

                    if(answer2.equals("quit")){
                        printIntro();
                        return;
                    }
                }catch(Exception error){
                    error.printStackTrace();

                }

                traslateToHashandResults(answer1, answer2, combos, player1, player2);

                System.out.println("player2 picks: " + answer2);
                System.out.println("User picks: " + answer1);

                String winOrLose = player1.getwOrL();
                System.out.println("you " + winOrLose +"!");

                addToHistory(history, player1, player2);
            }catch(Exception error){
                error.printStackTrace();
            }
        }
        //print instructions and loop back to main while loop
        printIntro();

    }

    //print game history from arrayList
    //
    private static void printHistory(ArrayList<String> history){
        System.out.println("\n");
        System.out.println("=== GAME HISTORY ===");

        for(String record: history) {
            System.out.println(record);
        }

        printIntro();

    }

    //add to game history arrayList
    //
    private static ArrayList<String> addToHistory(ArrayList<String> history, Player player1, Player player2){

        if(!player2.isHuman()) {
            history.add(player1.getwOrL() + " " + "Player-" + player1.getMove() + " computer-" + player2.getMove());
        }else{
            history.add(player1.getwOrL() + " " + "Player-" + player1.getMove() + " player2-" + player2.getMove());
        }
        return history;

    }

    //print to ask for move
    //
    private static void printAskForMove(){
        System.out.println("\n");
        System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
        System.out.println("Type 'quit' to go back to the Main Menu");
    }


    //generate random computer move
    private static String computerMove(){
        //computer random input
        Random rand = new Random();
        int randNum = rand.nextInt(2);
        String[] answerRay = new String[]{"rock", "paper", "Scissors"};
        String answer2 = answerRay[randNum];

        return answer2;
    }

    //take moves and get hashmap result
    //then update player values accordingly
    private static void traslateToHashandResults(String answer1, String answer2, HashMap<String,String> combos, Player player1, Player player2){
        //translate moves to hashmap result
        String hashmapKey = answer1 + " " +  answer2;
        String hashmapResult = combos.get(hashmapKey);
        String player2Result = "";


        //set player class attributes from this game for player1 and player2
        if(hashmapResult.equals("win")){
            player2Result = "lose";
            player1.setPoints(player1.getPoints() +1);
        }else{
            player2Result = "win";
            player2.setPoints(player2.getPoints() +1);
        }

        player1.setMove(answer1);
        player1.setwOrL(hashmapResult);


        player2.setMove(answer2);
        player2.setwOrL(player2Result);
    }


}
