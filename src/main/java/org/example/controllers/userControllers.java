package org.example.controllers;

import org.example.database.dbConnector;
import org.example.model.user.allCategory;
import org.example.model.user.category;
import org.example.model.user.userDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class userControllers {

    public allCategory getAllCategories(Integer account_id){
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement incomeCQ = con.prepareStatement("Select name from incomecategory where account_id = ?");
            PreparedStatement expenseCQ = con.prepareStatement("Select name from expensecategory where account_id = ?");

            incomeCQ.setInt(1,account_id);
            expenseCQ.setInt(1, account_id);

            ResultSet resultSetIncome = incomeCQ.executeQuery();
            ResultSet resultSetExpense = expenseCQ.executeQuery();

            List<category> incomeCategoryList = new ArrayList<>();
            List<category> expenseCategoryList = new ArrayList<>();
            int id = 1;
            while (resultSetIncome.next()){
                category newItem = new category(id, resultSetIncome.getString("name"));
                incomeCategoryList.add(newItem);
                id++;
            }
            id = 1;
            while (resultSetExpense.next()){
                category newItem = new category(id, resultSetExpense.getString("name"));
                expenseCategoryList.add(newItem);
                id++;
            }

            return new allCategory(incomeCategoryList, expenseCategoryList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new allCategory();
    }

    public String addIncomeCategory(Integer account_id, String categoryName){
        try {
            if(categoryName.trim().length() < 1){
                return "The category name is not valid name";
            }

            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement insertQuery = con
                    .prepareStatement("INSERT into incomecategory(account_id, name) values(?, ?)");

            insertQuery.setInt(1,account_id);
            insertQuery.setString(2, categoryName);

            int rows = insertQuery.executeUpdate();

            con = null;
            db.disconnectDB();
            if(rows > 0){
                return "The category is added Successfully added.";
            }
            return "An error has Occurred, Please try again.";

        } catch (Exception e){
            e.printStackTrace();
            return "An error has Occurred, Please try again.";
        }
    }

    public String addExpenseCategory(Integer account_id, String categoryName){
        try {
            if(categoryName.trim().length() < 1){
                return "The category name is not valid name";
            }

            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement insertQuery = con
                    .prepareStatement("INSERT into expensecategory(account_id, name) values(?, ?)");

            insertQuery.setInt(1,account_id);
            insertQuery.setString(2, categoryName);

            int rows = insertQuery.executeUpdate();

            con = null;
            db.disconnectDB();
            if(rows > 0){
                return "The category is added Successfully added.";
            }
            return "An error has Occurred, Please try again.";


        } catch (Exception e){
            e.printStackTrace();
            return "An error has Occurred, Please try again.";
        }
    }

    public userDetail getAllUserData(Integer account_id){
        try {
            String name = "", email = "", dob = "";
            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement userQuery = con.prepareStatement("Select name, email, dob from useraccounts where account_id = ?");
            userQuery.setInt(1, account_id);

            allCategory category = getAllCategories(account_id);
            ResultSet resultSet = userQuery.executeQuery();

            if(resultSet.next()){
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                dob = resultSet.getString("dob");
            }
            userDetail userData = new userDetail(name, email, dob, category.getIncomeCategory(), category.getExpenseCategory());
            return userData;

        } catch (Exception e){
            e.printStackTrace();
            return new userDetail();
        }
    }

}
