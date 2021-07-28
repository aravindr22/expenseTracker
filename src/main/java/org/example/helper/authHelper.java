package org.example.helper;

import org.example.database.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class authHelper {

    public Integer getIDFromDB(String email, String password){
        try {
            int rows=-1;
            dbConnector db = new dbConnector();
            Connection con = db.con;
            PreparedStatement query = con
                    .prepareStatement("Select account_id, password from useraccounts where email=?");

            query.setString(1, email.trim());

            ResultSet resultSet = query.executeQuery();
            if(resultSet.next()){
                String pass = resultSet.getString("password");
                if(pass.equals(password)){
                    return resultSet.getInt("account_id");
                }
            }
            con = null;
            db.disconnectDB();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }
}
