package org.example.controllers;

import org.example.database.dbConnector;
import org.example.model.user.allCategory;
import org.example.model.user.category;

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
}
