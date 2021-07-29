package org.example.model.transaction;

import java.sql.Timestamp;

public class transactionModel {
    private Integer id;
    private String categoryName;
    private String expenseType;
    private Integer amount;
    private String description;
    private Timestamp timestamp;

    public transactionModel() {
    }

    public transactionModel(Integer id, String categoryName, String expenseType, Integer amount, String description, Timestamp timestamp) {
        this.id = id;
        this.categoryName = categoryName;
        this.expenseType = expenseType;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
