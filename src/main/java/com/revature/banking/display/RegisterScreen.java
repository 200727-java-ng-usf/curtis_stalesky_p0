package com.revature.banking.display;

import com.revature.banking.models.AppUsers;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.banking.AppDriver.app;

/*
This class will Register new users.
*/
public class RegisterScreen extends Screen {

    private UserServices userServices;

    public RegisterScreen(UserServices userService){
        super("RegisterScreen", "/register");
        //System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        this.userServices = userServices;
    }


    @Override
    public void render() {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String firstName, lastName, username, password, email;

        try{
            // This section will prompt the user to supply necessary
            // information to create an account
            // TODO Need to adjust this section to ask for user info for banking app
            System.out.println("Sign up for a new account!");
            System.out.print("First name: ");
            firstName = console.readLine();
            System.out.print("Last name: ");
            lastName = console.readLine();
            System.out.print("Username: ");
            username = console.readLine();
            System.out.print("Password: ");
            password = console.readLine();
            System.out.println("Email");
            email = console.readLine();

            //TODO need to include all necessary params from AppUsers so birthdate needs to be added
            AppUsers newUser = new AppUsers(firstName, lastName, username, password, email);
            userServices.register(newUser);
            System.out.println(newUser);

            if (app.isSessionValid()){
                app.getRouter().navigate("/dashboard");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
