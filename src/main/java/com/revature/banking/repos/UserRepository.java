package com.revature.banking.repos;

import com.revature.banking.models.AppUsers;
import com.revature.banking.models.Roles;
import com.revature.banking.util.ConnectionFactory;


// importing java sql and util objects
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/** The User Repository deals with user data such as retrieving user info and saving new user info  */

public class UserRepository {

    private int accountNumber = 1000; // will store a temporary account number that will be used to create an
                               // account number for new customer in the accounts table
    // TODO update the base query string for revabanking app. or remove
    private String baseQuery = "SELECT * FROM revabooks.app_users au " +
            "                   JOIN revabooks.user_roles ur " +
            "                   ON au.role_id = ur.id ";

    //private UserDB userDataset = UserDB.userDataset;
    // TODO replace mock implementation with in-memory persistence -Done

    public UserRepository() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    /* Method that finds users by their username and password */
    public Optional<AppUsers> findUserByCredentials(String username, String password){

        Optional<AppUsers> _user = Optional.empty();

        // This is a try with resources, will close connection when try block is done due to autoclose()
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            // TODO change sql string to access revabanking -- Completed
            String sql = "SELECT * FROM revabanking.customers WHERE customers.user_name = ? " +
                         "AND customers.user_password = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return _user;
    }

    // This method will find a user by their username
    public Optional<AppUsers> findUserByUsername(String username){

        Optional<AppUsers> _user = Optional.empty();

        try(Connection  conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM revabanking.customers WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);


            ResultSet rs = pstmt.executeQuery(); // only returns 1 record from the DB
            _user = mapResultSet(rs).stream().findFirst();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return _user;
    }

    // Sends the new user info into the database
    // TODO Need to change the save information to save account users into the revabanking database
    public void save(AppUsers newUser){

        Optional<AppUsers> _user = Optional.empty();
        // TODO need to adjust sql string to match with revabanking database
        // TODO also need to make sure that account information is also inserted into the accounts table
        if (!newUser.getUserName().isEmpty()){
            try(Connection  conn = ConnectionFactory.getInstance().getConnection()){

                // This part will insert new user information into the accounts table
                String sql = "INSERT INTO revabanking.customers (first_name, last_name, " +
                        "user_name, user_password, email)" +
                        "VALUES (?, ?, ?, ?, ?);" +
                        "INSERT INTO revabanking.customers";

                /*String sql = "INSERT INTO revabanking.customers (username, password, " +
                             "first_name, last_name, email, role_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";*/ // reference string,

                // second parameter here is used to indicate column names that will have generated values
                PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
                pstmt.setString(1, newUser.getFirstName());
                pstmt.setString(2, newUser.getLastName());
                pstmt.setString(3, newUser.getUserName());
                pstmt.setString(4, newUser.getPassword());
                pstmt.setString(5, newUser.getEmail());
                //pstmt.setInt(6, newUser.getRole().ordinal() +  4);

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted != 0){
                    ResultSet rs = pstmt.getGeneratedKeys();

                    while (rs.next()){
                        newUser.setId(rs.getInt(1));
                    }
                }
            }catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }else {
            System.err.println("User already exits");
        }
        // This part will insert new user information into the accounts table
        accountNumber = ++accountNumber;
        if (!newUser.getUserName().isEmpty()){
            try(Connection  conn = ConnectionFactory.getInstance().getConnection()){

                // need to fix sql string to input into accounts table and accounts table. 2
                String sql = "INSERT INTO revabanking.accounts ("+ accountNumber +" ac_firstname, ac_lastname, " +
                        "balance)" +
                        "VALUES (?, ?, ?, ?);" +
                        "INSERT INTO revabanking.accounts";

                // second parameter here is used to indicate column names that will have generated values
                PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
                pstmt.setInt(1, accountNumber);
                pstmt.setString(2, newUser.getFirstName());
                pstmt.setString(3, newUser.getLastName());
                pstmt.setDouble(4, newUser.getAccountBalance(0.00));

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted != 0){
                    ResultSet rs = pstmt.getGeneratedKeys();

                    while (rs.next()){
                        newUser.setId(rs.getInt(1));
                    }
                }
            }catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }else {
            System.err.println("User already exits");
        }


    }

    //TODO need to alter mapResultSet to include account information?
    private Set<AppUsers> mapResultSet(ResultSet rs)throws SQLException{

        Set<AppUsers> users = new HashSet<>();
        while (rs.next()){
            AppUsers temp = new AppUsers();
            temp.setId(rs.getInt("customer_id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setUserName(rs.getString("user_name")); // previous field username
            temp.setPassword(rs.getString("user_password")); // previous field password
            // temp.setRole(Roles.getByName(rs.getString("name")));
            System.out.println(temp);
            users.add(temp);
        }
        return users;
    }
}
