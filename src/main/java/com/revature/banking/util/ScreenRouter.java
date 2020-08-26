package com.revature.banking.util;


import com.revature.banking.display.Screen;

import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {

    private Set<Screen> screens = new HashSet<>();

    public Set<Screen> getScreens() {
        return screens;
    }

    public ScreenRouter addScreen(Screen screen){
        //System.out.println("[LOG] - Loading" + screen.getName() + " into the router");
        screens.add(screen);
        return this;
    }

    public void navigate(String route){
        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found with that route"))
                .render();
    }
}
