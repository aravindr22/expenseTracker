package org.example.model.transaction;

import java.util.List;

public class transactionListModel {
    private Integer page;
    private List<transactionModel> transactions;

    public transactionListModel() {
    }

    public transactionListModel(Integer page, List<transactionModel> transactions) {
        this.page = page;
        this.transactions = transactions;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<transactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<transactionModel> transactions) {
        this.transactions = transactions;
    }
}
