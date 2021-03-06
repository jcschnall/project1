package com.company;

import java.io.*;

//class for password masking for two player game
//

class EraserThread implements Runnable {
    private boolean stop;

    /**
     *The prompt displayed to the user
     */
    public EraserThread(String prompt) {
        System.out.print(prompt);
    }

    /**
     * Begin masking...display asterisks (*)
     */
    public void run () {
        stop = true;
        while (stop) {
            System.out.print("\010*");
            //System.out.print("\r***************************");
            try {
                Thread.currentThread().sleep(1);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * Instruct the thread to stop masking
     */
    public void stopMasking() {
        this.stop = false;
    }
}