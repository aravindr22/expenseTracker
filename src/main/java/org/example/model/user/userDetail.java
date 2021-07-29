package org.example.model.user;

import java.util.List;

public class userDetail {
    private String name;
    private String email;
    private String dob;
    private List<category> incomeCategory;
    private List<category> expenseCategory;

    public userDetail() {
    }

    public userDetail(String name, String email, String dob, List<category> incomeCategory, List<category> expenseCategory) {
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

    public List<category> getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(List<category> incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public List<category> getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(List<category> expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
