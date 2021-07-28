package org.example.helper;

import org.example.database.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.StringTokenizer;

public class authHelper {

    public Integer getIDFromDB(String email, String password){
        try {
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

    public boolean loginUser(int account_id){
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;
            PreparedStatement query = con
                    .prepareStatement("UPDATE useraccounts set isloggedin = 'true' where account_id = ?");
            query.setInt(1, account_id);
            int rows = query.executeUpdate();
            if(rows < 1){
                return false;
            }
            con = null;
            db.disconnectDB();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkloggedIn(String email, Integer account_id){
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;
            PreparedStatement query = con
                    .prepareStatement("Select isloggedin from useraccounts where account_id=? and email=?");
            query.setInt(1, account_id);
            query.setString(2, email);

            ResultSet resultSet = query.executeQuery();

            con = null;
            db.disconnectDB();
            if(resultSet.next()){
                if(resultSet.getString("isloggedin").equals("t")){
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Integer decodeAccountID(String authToken){
        try{
            authToken = authToken.replaceFirst("Basic ", "");
            byte[] decodedBytes = Base64.getDecoder().decode(authToken);
            String decodedString = new String(decodedBytes);
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String email = tokenizer.nextToken();
            return Integer.parseInt(tokenizer.nextToken());
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}
