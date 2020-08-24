package com.revature.banking.display;

/* Main Login that will direct current users to log in and
   new users to register for an account */

import static com.revature.banking.AppDriver.app;

public class MainScreen extends Screen{

    public MainScreen(){
        super("MainScreen", "/main");
    }

    // render is the menu selection method that will navigate
    // the user to various parts of the banking app
    @Override
    public void render() {
        System.out.println("Welcome to Reva Banking");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit Application");

        try {
            System.out.println("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/login"); //TODO implement the app function
                    break;
                case "2":
                    app.getRouter().navigate("/register");
                    break;
                case "3":
                    app.setAppRunning(false);
                    break;
                default:
                    System.out.println("[LOG] - Invalid selection!");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
