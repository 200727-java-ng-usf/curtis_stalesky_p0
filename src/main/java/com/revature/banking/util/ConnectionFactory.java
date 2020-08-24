package com.revature.banking.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connFactory = new ConnectionFactory(); // eager singleton

    private Properties props = new Properties();

    private ConnectionFactory(){

        try {
            props.load(new FileReader("./src/main/resources/application.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance(){
        return  connFactory;
    }

    public Connection getConnection() {

        Connection conn = null; // The connection is assigned to null

        try {
            // Force the JVM to load the PostGreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        // Possibly the issue that doesn't let the program work as if is equal to null.
        if (conn == null){
            throw new RuntimeException("Failed to establish connection");
        }

        return conn;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return super.clone();
        throw new CloneNotSupportedException();
    }




}
