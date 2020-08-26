package com.revature.banking.models;

import java.util.Objects;

public class AppUsers {

    // variables/fields that will be storing user login information.
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private double accountBalance;
    private int accountNumber;
    private Roles role;


       /* Constructors */
    // Full constructor
    public AppUsers(Integer id, String firstName, String lastName, String userName,
                    String password,String email, Roles role)
    {
        this(firstName, lastName, userName, password);
        this.id = id;
        this.role = role;
        this.email = email;

    }

    // User information constructor
    public AppUsers(String firstName, String lastName, String userName, String password)
    {
       this.firstName = firstName;
       this.lastName = lastName;
       this.userName = userName;
       this.password = password;
    }
    // User information with roles, may be used for manager/admin purposes
    public AppUsers(String firstName, String lastName, String userName, String password,
                    String email)
    {
        this(firstName, lastName, userName, password);
        this.email = email;

    }
    public AppUsers(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    public AppUsers(int accountNumber){ this.accountNumber = accountNumber; }
    // Empty Constructor
    public AppUsers(){super();}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public double getAccountBalance(double accountBalance) { return accountBalance;}

    public void setAccountBalance(double accountBalance) {this.accountBalance = accountBalance;}

    public int getAccountNumber() {return accountNumber;}

    public void setAccountNumber() {this.accountNumber = accountNumber;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUsers appUsers = (AppUsers) o;
        return Objects.equals(id, appUsers.id) &&
                Objects.equals(firstName, appUsers.firstName) &&
                Objects.equals(lastName, appUsers.lastName) &&
                Objects.equals(userName, appUsers.userName) &&
                Objects.equals(password, appUsers.password) &&
                Objects.equals(email, appUsers.email) &&
                role == appUsers.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName, password, email, role);
    }

    @Override
    public String toString() {
        return "AppUsers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
