package org.example.model.transaction;

public class transactionViewModel {
    private String categoryName;
    private String expenseType;
    private Integer amount;
    private String description;

    public transactionViewModel() {
    }

    public transactionViewModel(String categoryName, String expenseType, Integer amount, String description) {
        this.categoryName = categoryName;
        this.expenseType = expenseType;
        this.amount = amount;
        this.description = description;
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
}
