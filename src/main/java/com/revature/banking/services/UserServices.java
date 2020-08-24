package com.revature.banking.services;

import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.models.*;
import com.revature.banking.repos.UserRepository;
import com.revature.banking.models.Roles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.banking.AppDriver.app;

public class UserServices {

    private UserRepository userRepo;

    // Instantiates this class
    public UserServices(UserRepository repo){
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        userRepo = repo;

    }
    // validate that the provided username and password are non-null values
    public void authenticate(String username, String password)
            throws javax.naming.AuthenticationException {

        if( username == null || username.trim().equals("") ||
                password == null || password.trim().equals("")){
            // TODO implement a custom InvalidRequestException
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        AppUsers authUser = userRepo.findUserByCredentials(username, password)
                .orElseThrow(javax.naming.AuthenticationException::new);

        app.setCurrentUser(authUser);
    }

    // This method takes in the user information and uses that information to create a new user in the
    // database
    public void register(AppUsers newUser){

        if (!isUserValid(newUser)){
            // TODO implement a custom InvalidRequestException
            throw new RuntimeException("Invalid user field values provided during registration!");

        }
        // This checks to see if the user is already exists in the DB and is unique
        Optional<AppUsers> existingUser = userRepo.findUserByUsername(newUser.getUserName());
        if (existingUser.isPresent()){
            // TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided username is already in use!");
        }

        newUser.setRole(Roles.ACTIVE); // New users and current users are by default set to ACTIVE
        userRepo.save(newUser);
        System.out.println(newUser);
        //return userRepo.save(newUser);
        app.setCurrentUser(newUser);
    }

    public Set<AppUsers> getAllUsers(){
        return new HashSet<>();
    }
    public Set<AppUsers> getUsersByRole(){
        return null;
    }
    public Set<AppUsers> getUsersByID(int id){
        return null;
    }

    public Set<AppUsers> getUsersByUsername(String username){
        return null;
    }

    public boolean deletedUserById(int id){
        return false;
    }

    public AppUsers update(AppUsers updateUser){
        return null;
    }
    /**
     * Validates that the given user and its fields are valid (not null or empty strings). Does
     * not perform validation on id or role fields.
     *
     * @param user
     * @return true or false depending on if the user was valid or not
     */

    // boolean method that just checks if the user is valid or not
    public boolean isUserValid(AppUsers user){

        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUserName() == null || user.getUserName().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }

    //TODO need to implement a balance retrieval method
    //TODO need to implement a method that withdraw and deposit
}
