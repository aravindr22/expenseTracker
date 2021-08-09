package org.example.model.transaction.stats;

public class transactionCategoryModel {
    private String categoryName;
    private Integer amount;

    public transactionCategoryModel() {
    }

    public transactionCategoryModel(String categoryName, Integer amount) {
        this.categoryName = categoryName;
        this.amount = amount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
