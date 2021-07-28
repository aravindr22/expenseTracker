package org.example.controllers;

import org.example.database.dbConnector;
import org.example.helper.authHelper;
import org.example.model.loginModel;
import org.example.model.messageIdModel;
import org.example.model.registerModel;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Base64;

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

    public messageIdModel loginUser(loginModel data){
        authHelper auth = new authHelper();
        int account_id = auth.getIDFromDB(data.getEmail().trim(), data.getPassword().trim());
        if(account_id > 0){
            if(auth.loginUser(account_id)){
                String code, pass, header;
                code = data.getEmail() + ":" + account_id;
                pass = Base64.getEncoder().encodeToString(code.getBytes(StandardCharsets.UTF_8));
                header = "Basic " + pass;
                return new messageIdModel("LoggedIn Succesfully", header);
            } else  {

                return new messageIdModel("An Error has occured please try again", "");
            }
        } else {
            return new messageIdModel("You Haven't Registered or Credentials are invalid", "");
        }
    }
}
