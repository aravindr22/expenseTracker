package org.example.model;

public class loginModel {
    private Integer id;
    private String email;
    private String password;

    public loginModel() {
    }

    public loginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public loginModel(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
