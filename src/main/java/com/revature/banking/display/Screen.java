package com.revature.banking.display;

public abstract class Screen {

    private String name;
    private String route;

    protected Screen(String name, String route){
        this.name = name;
        this.route = route;
    }

    // Get method to get the name of the screen
    public String getName() { return name; }

    // Get method to get the name of the router
    public String getRoute(){ return route; }

    public abstract void render();



}
