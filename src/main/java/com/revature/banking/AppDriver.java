package com.revature.banking;

import com.revature.banking.util.AppState;

public class AppDriver {

    public static AppState app = new AppState();

    public static void main(String[] args) {

        // while loop that will keep printing out main screen
        while(app.isAppRunning()){
            //System.out.println("beginning of while loop");
            app.getRouter().navigate("/main"); // First screen to pop-up
            //System.out.println("end of while loop");
        }

    }
}
