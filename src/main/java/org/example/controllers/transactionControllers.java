package org.example.controllers;

import org.example.database.dbConnector;
import org.example.model.transaction.transactionViewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class transactionControllers {

    public String addTransaction(Integer account_id, transactionViewModel data){
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;

            if (!data.getExpenseType().trim().toLowerCase().equals("income") && !data.getExpenseType().trim().toLowerCase().equals("expense")) {
                return "Wrong Category Names";
            }

            PreparedStatement transactionViewInsertQuery = con
                    .prepareStatement("Insert into transactionview(categoryname, expensetype, amount, description) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            transactionViewInsertQuery.setString(1, data.getCategoryName());
            transactionViewInsertQuery.setString(2, data.getExpenseType());
            transactionViewInsertQuery.setInt(3, data.getAmount());
            transactionViewInsertQuery.setString(4, data.getDescription());

            int rows = transactionViewInsertQuery.executeUpdate();
            ResultSet resultSet;

            if (rows < 1){
                return "Oops!!, An error has occurred please try again";
            } else {
                resultSet = transactionViewInsertQuery.getGeneratedKeys();
            }

            int transactionView_id;
            if(resultSet.next()){
               transactionView_id = resultSet.getInt(1);
            } else {
                return "Oops!!, An error has occurred please try again";
            }

            PreparedStatement transactionInsertQuery = con
                    .prepareStatement("Insert into transaction(account_id, transactionview_id) values(?,?)");
            transactionInsertQuery.setInt(1, account_id);
            transactionInsertQuery.setInt(2, transactionView_id);

            rows = transactionInsertQuery.executeUpdate();
            if(rows < 1){
                return "Oops!!, An error has occurred please try again";
            }

            PreparedStatement ledgerQuery = con
                    .prepareStatement("Select income, expense, balance from ledger where account_id = ?");
            ledgerQuery.setInt(1, account_id);

            int income, expense, balance;
            resultSet = ledgerQuery.executeQuery();
            if (resultSet.next()){
                income = resultSet.getInt("income");
                expense = resultSet.getInt("expense");
                balance = resultSet.getInt("balance");
            } else {
                return "Oops!!, An error has occurred please try again";
            }

            if(data.getExpenseType().trim().toLowerCase().equals("income")){
                income = income + data.getAmount();
                balance = balance + data.getAmount();
            } else {
                expense = expense + data.getAmount();
                balance = balance - data.getAmount();
            }

            PreparedStatement updateLedgerQuery = con
                    .prepareStatement("UPDATE ledger set income = ?, expense = ?, balance = ? where account_id = ?");
            updateLedgerQuery.setInt(1, income);
            updateLedgerQuery.setInt(2, expense);
            updateLedgerQuery.setInt(3, balance);
            updateLedgerQuery.setInt(4, account_id);

            rows = updateLedgerQuery.executeUpdate();

            if(rows < 1){
                return "Oops!!, An error has occurred please try again";
            }

            return "Your Transaction Successfully added";
        } catch (Exception e){
            e.printStackTrace();
            return "Oops!!, An error has occurred please try again";
        }
    }
}
