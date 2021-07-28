package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnector {
    public Connection con;
    public dbConnector() {
        connectDB();
    }

    private void connectDB(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/expenseTracker", "postgres", "root");
            if(con != null){
//                System.out.println("Connected");
            } else {
                System.out.println("Not Connected");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disconnectDB(){
        try {
            con.close();
            con = null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
