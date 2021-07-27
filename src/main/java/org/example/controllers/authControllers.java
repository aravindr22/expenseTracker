package org.example.controllers;

import org.example.database.dbConnector;
import org.example.model.registerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class authControllers {

    public int registerUser(registerModel user){
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;
            PreparedStatement query = con.prepareStatement(
                    "insert into useraccounts(name, email, password, dob) values(?,?,?,?)");

            query.setString(1, user.getName());
            query.setString(2, user.getEmail());
            query.setString(3, user.getPassword());
            query.setString(4, user.getDob());
            int rows = query.executeUpdate();
            if(rows < 1){
                return 0;
            }
            con = null;
            db.disconnectDB();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
