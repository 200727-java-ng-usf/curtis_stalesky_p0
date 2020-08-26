package com.revature.banking.display;

import com.revature.banking.models.AppUsers;
import com.revature.banking.services.UserServices;

import static com.revature.banking.AppDriver.app;

public class DashboardScreen extends Screen{

    String name = "DashboardScreen";
    String route = "/dashboard";


    public DashboardScreen(){
        super("DashboardScreen", "/dashboard");
    }
    @Override
    public void render() {
        String firstName = app.getCurrentUser().getFirstName();
        String lastName = app.getCurrentUser().getLastName();

        // this while loop will keep the dashboard screen open
        while (app.isSessionValid()) {


            System.out.println("Welcome to your Dashboard! " + firstName + " " + lastName);
            // TODO will need to create a method to view account in userServices
            System.out.println("1) View your Account");
            // TODO will need to create a method for the user to withdraw money, can't withdraw with 0 balance
            System.out.println("2) Make a withdraw");
            // TODO will need to create a method for the user to deposit money
            System.out.println("3) Make a deposit");
            System.out.println("4) Log Out");


            try {
                System.out.println("> ");
                String userSelection = app.getConsole().readLine();


                switch (userSelection) {
                    case "1": // View account info
                    {
                        // TODO use this block to view their account
                        System.out.println("Function not yet implemented");

                    }
                        break;
                    case "2": // Make a withdraw
                    {
                        // TODO use this block to make a withdraw
                        System.out.println("Function not yet implemented");
                    }

                        break;
                    case "3": // Make a deposit
                    {
                        // TODO use this block to make a deposit
                        System.out.println("Function not yet implemented");
                    }
                        break;
                    case "4":
                    {
                        // Logs out of the dashboard and back to the log in screen
                        app.getRouter().navigate("/login");
                    }
                    default:
                        //
                        System.out.println("[LOG] - Invalid selection!");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
