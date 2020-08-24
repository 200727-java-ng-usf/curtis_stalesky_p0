package com.revature.banking;

import com.revature.banking.util.AppState;

public class AppDriver {

    public static AppState app = new AppState();

    public static void main(String[] args) {

        // while loop that will keep printing out
        while(app.isAppRunning()){
            System.out.println("beginning of while loop");
            app.getRouter().navigate("/main");
            System.out.println("end of while loop");
        }

    }
}
