package com.company;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/*
To do
-check and make sure exceptions work okay
-comment
-add in extras??  NAAA
-git repository

 */

public class Main {

    public static void main(String[] args) {
        boolean quit = false;
        HashMap<String, String> combos = makeCombos();
        ArrayList<String> history = new ArrayList<>();
        String playerType = "";

        printIntro();

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
                if (gameMode.equals("quit")) {
                    quit = true;
                }
            }catch(Exception error){
                error.printStackTrace();
            }
        }



    }

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

    private static void printIntro(){
        System.out.println("Welcome to Rock Paper Scissors!");
        System.out.println("MAIN MENU");
        System.out.println("=====");
        System.out.println("1. Type \'play_computer\' or 'play_human' to play");
        System.out.println("2. Type \'history\' to view your game history");
        System.out.println("Type \'quit\' to stop playing");

    }

    private static void play(HashMap<String, String> combos, ArrayList<String> history, String playerType){


        Scanner s = new Scanner(System.in);

        if(playerType.equals("play_computer")){

            Player player1 = new Player(true,1);
            Player player2 = new Player(false, 2);

            System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
            System.out.println("Type 'quit' to go back to the Main Menu");

            String answer1 = "";

            try {
                answer1 = s.next();
                answer1.toLowerCase();

                while(!(answer1.toLowerCase().equals("rock") || answer1.toLowerCase().equals("paper") || answer1.toLowerCase().equals("history") ||
                        answer1.equals("quit"))){
                    printAskForMove();
                    answer1 = s.next();
                }

                if(answer1.equals("quit")){
                    printIntro();
                    return;
                }



                Random rand = new Random();
                int randNum = rand.nextInt(2);
                String[] answerRay = new String[]{"rock", "paper", "Scissors"};
                String answer2 = answerRay[randNum];

                String hashmapKey = answer1 + " " +  answer2;
                String hashmapResult = combos.get(hashmapKey);
                String player2Result = "";


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


                System.out.println("Computer picks: " + answer2);
                System.out.println("User picks: " + answer1);

                String winOrLose = player1.getwOrL();
                System.out.println("you " + winOrLose +"!");

                addToHistory(history, player1, player2);
            }catch(Exception error){
                error.printStackTrace();
            }

        }else{

            Player player1 = new Player(true,1);
            Player player2 = new Player(true, 2);

            System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
            System.out.println("Type 'quit' to go back to the Main Menu");

            String answer1 = "";
            try {
                //answer1 = s.next();


                PasswordField getMove = new PasswordField();
                answer1 = getMove.readPassword("");
                answer1.toLowerCase();
                System.out.println("debug" + answer1);



                while(!(answer1.toLowerCase().equals("rock") || answer1.toLowerCase().equals("paper") || answer1.toLowerCase().equals("history") ||
                        answer1.equals("quit"))){

                    System.out.println("debug" + "in while");
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

                    getMove = new PasswordField();
                    answer1 = getMove.readPassword("");
                    answer2.toLowerCase();

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



                String hashmapKey = answer1 + " " +  answer2;
                String hashmapResult = combos.get(hashmapKey);
                String player2Result = "";

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


                System.out.println("player2 picks: " + answer2);
                System.out.println("User picks: " + answer1);

                String winOrLose = player1.getwOrL();
                System.out.println("you " + winOrLose +"!");



                addToHistory(history, player1, player2);
            }catch(Exception error){
                error.printStackTrace();
            }


        }

        printIntro();



    }

    private static void printHistory(ArrayList<String> history){

        System.out.println("=== GAME HISTORY ===");
        //System.out.println("WIN: Player-rock computer-scissors");
        //System.out.println("LOSS: Player-paper computer-scissors");

        for(String record: history) {
            System.out.println(record);
        }

        printIntro();

    }

    private static ArrayList<String> addToHistory(ArrayList<String> history, Player player1, Player player2){

        history.add(player1.getwOrL() + " " + "Player-" + player1.getMove() + "computer-" + player2.getMove());
        return history;

    }

    private static void printAskForMove(){
        System.out.println("Type in 'rock' 'paper' or 'scissors' to play.");
        System.out.println("Type 'quit' to go back to the Main Menu");
    }



}
