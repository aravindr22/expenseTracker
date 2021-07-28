package org.example.model.user;

import java.util.List;

public class userDetail {
    private String name;
    private String email;
    private String dob;
    private List<String> incomeCategory;
    private List<String> expenseCategory;

    public userDetail() {
    }

    public userDetail(String name, String email, String dob, List<String> incomeCategory, List<String> expenseCategory) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.incomeCategory = incomeCategory;
        this.expenseCategory = expenseCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List<String> getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(List<String> incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public List<String> getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(List<String> expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
