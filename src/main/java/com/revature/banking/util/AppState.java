package com.revature.banking.util;

import com.revature.banking.display.*;
import com.revature.banking.models.AppUsers;
import com.revature.banking.repos.UserRepository;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader console;
    private AppUsers currentUser;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState (){
        System.out.println("[LOC] - Initializing application");

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final UserServices userService = new UserServices(userRepo);

        // This screen router is instantiating all the screens that this program will be using.
        // any new screens we use will need to be add here to ensure that they can work.
        router = new ScreenRouter();
        router.addScreen(new MainScreen())
                .addScreen(new RegisterScreen(userService))
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashboardScreen());

        System.out.println("[LOG] - Application initialization complete");

    }

    public BufferedReader getConsole() {
        return console;
    }

    public AppUsers getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUsers currentUser){ this.currentUser = currentUser;}

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public void invalidateCurrentSession(){
        currentUser = null;
    }

    public boolean isSessionValid(){
        return (this.currentUser != null);
    }
}
