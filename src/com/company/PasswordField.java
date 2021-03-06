package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


//class for password masking for two player game
//

public class PasswordField {

    /**
     *@param prompt The prompt to display to the user
     *@return The password as entered by the user
     */
    public String readPassword (String prompt) {
        EraserThread et = new EraserThread(prompt);
        Thread mask = new Thread(et);


        try {
            mask.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mask.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";


        try {
            password = in.readLine();
            //System.out.println("debug " + password);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // stop masking
        et.stopMasking();
        // return the password entered by the user

        return password;
    }
}
