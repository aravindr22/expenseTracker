package org.example.controllers;

import org.example.database.dbConnector;
import org.example.helper.authHelper;
import org.example.model.registerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class authControllers {

    public String registerUser(registerModel user){
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;
            PreparedStatement insertQuery = con.prepareStatement(
                    "insert into useraccounts(name, email, password, dob) values(?,?,?,?)");

            insertQuery.setString(1, user.getName().trim());
            insertQuery.setString(2, user.getEmail().trim());
            insertQuery.setString(3, user.getPassword().trim());
            insertQuery.setString(4, user.getDob().trim());
            int rows = insertQuery.executeUpdate();
            if(rows < 1){
                return "There is a problem while registering please try again";
            }
            con = null;
            db.disconnectDB();
        } catch (Exception e){
            e.printStackTrace();
            return "There is a problem while registering please try again";
        }
        return "The User registered Successfully, Please Log In to continue ";
    }
}
