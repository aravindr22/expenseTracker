package org.example.controllers;

import org.example.database.dbConnector;
import org.example.model.transaction.transactionListModel;
import org.example.model.transaction.transactionModel;
import org.example.model.transaction.transactionStatsModel;
import org.example.model.transaction.transactionViewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

            con = null;
            db.disconnectDB();

            return "Your Transaction Successfully added";
        } catch (Exception e){
            e.printStackTrace();
            return "Oops!!, An error has occurred please try again";
        }
    }

    public transactionStatsModel getTransactionStats(Integer account_id){
        transactionStatsModel data = new transactionStatsModel();
        try {
            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement ledgerStats = con
                    .prepareStatement("Select income, expense, balance from ledger where account_id = ?");
            ledgerStats.setInt(1, account_id);

            ResultSet resultSet = ledgerStats.executeQuery();
            if(resultSet.next()){
                data.setIncome(resultSet.getInt("income"));
                data.setExpense(resultSet.getInt("expense"));
                data.setBalance(resultSet.getInt("balance"));
            } else {
                return data;
            }

            PreparedStatement countStats = con
                    .prepareStatement("select count(*) from transaction where account_id = ?");
            countStats.setInt(1, account_id);

            resultSet = countStats.executeQuery();
            if(resultSet.next()){
                data.setTransactionCount(resultSet.getInt("count"));
            } else {
                return data;
            }
            con = null;
            db.disconnectDB();

            return data;
        } catch (Exception e){
            e.printStackTrace();
            return data;
        }
    }

    public transactionListModel getAllTransactions(Integer account_id, Integer page){
        try {
            if(page < 1){
                page = 0;
            }
            String sqlTemplate = "select categoryname, expensetype, amount, description, timestamp from transaction as t inner join transactionview as tv on t.transactionview_id = tv.transactionview_id where account_id = ? offset <offset> limit 5;";
            int offsetPage = (page-1) * 5;
            String selectTransactionSql = sqlTemplate.replace("<offset>", new String(String.valueOf(offsetPage)));
            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement getTransactionQuery = con
                    .prepareStatement(selectTransactionSql);
            getTransactionQuery.setInt(1, account_id);

            ResultSet resultSet = getTransactionQuery.executeQuery();
            List<transactionModel> transactionList = new ArrayList<>();
            int id = 1;
            while (resultSet.next()){
                transactionModel txn = new transactionModel();
                txn.setId(id);
                txn.setCategoryName(resultSet.getString("categoryname"));
                txn.setExpenseType(resultSet.getString("expensetype"));
                txn.setAmount(resultSet.getInt("amount"));
                txn.setDescription(resultSet.getString("description"));
                txn.setTimestamp(resultSet.getTimestamp("timestamp"));
                transactionList.add(txn);
                id++;
            }
            con = null;
            db.disconnectDB();

            return new transactionListModel(page, transactionList);

        } catch (Exception e){

        }
        return new transactionListModel();
    }

    public transactionListModel transactionListByCategory(Integer account_id, Integer page, String category){
        try {
            if(category.trim().equals("")){
                return getAllTransactions(account_id, page);
            }
            category = category.trim();
            String sqlTemplate = "select categoryname, expensetype, amount, description, timestamp from transaction as t inner join transactionview as tv on t.transactionview_id = tv.transactionview_id where account_id = ? and categoryname = ? offset <offset> limit 5;";
            int offsetPage = (page-1) * 5;
            String selectTransactionSql = sqlTemplate.replace("<offset>", new String(String.valueOf(offsetPage)));
            dbConnector db = new dbConnector();
            Connection con = db.con;

            PreparedStatement getTransactionQuery = con
                    .prepareStatement(selectTransactionSql);
            getTransactionQuery.setInt(1, account_id);
            getTransactionQuery.setString(2, category);

            ResultSet resultSet = getTransactionQuery.executeQuery();
            List<transactionModel> transactionList = new ArrayList<>();
            int id = 1;
            while (resultSet.next()){
                transactionModel txn = new transactionModel();
                txn.setId(id);
                txn.setCategoryName(resultSet.getString("categoryname"));
                txn.setExpenseType(resultSet.getString("expensetype"));
                txn.setAmount(resultSet.getInt("amount"));
                txn.setDescription(resultSet.getString("description"));
                txn.setTimestamp(resultSet.getTimestamp("timestamp"));
                transactionList.add(txn);
                id++;
            }
            con = null;
            db.disconnectDB();
            return new transactionListModel(page, transactionList);

        } catch (Exception e){

        }
        return new transactionListModel();
    }
}
