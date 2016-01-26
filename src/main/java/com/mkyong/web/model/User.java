package com.mkyong.web.model;

public class User {

    String email;
    int loginFailed;

    public User(String email, int loginFailed) {
        this.email = email;
        this.loginFailed = loginFailed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(int loginFailed) {
        this.loginFailed = loginFailed;
    }
}
