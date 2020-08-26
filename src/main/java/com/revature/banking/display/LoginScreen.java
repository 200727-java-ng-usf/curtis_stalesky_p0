package com.revature.banking.display;


import com.revature.banking.services.UserServices;

import javax.naming.AuthenticationException;
import java.io.IOException;

import static com.revature.banking.AppDriver.app;

public class LoginScreen extends Screen{

    private UserServices userServices;

    public LoginScreen(UserServices userServices){
        super("LoginScreen", "/login");

        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // Loosely coupled, because this class is not responsible for instantiation of a UserService
        this.userServices = userServices;

        // userService = new UserService();
    }


    // This class/screen will prompt the user to log into the app
    @Override
    public void render() {
        String username, password; // Strings that will hold the username and password

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            userServices.authenticate(username, password);

            if(app.isSessionValid())app.getRouter().navigate("/dashboard");

        } catch (IOException | AuthenticationException ioe){
            ioe.printStackTrace();
        }

    }
}
