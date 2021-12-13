package com.example.sqlnetwork.domain;

public class Token {
    private String account;
    private String password;

    public Token(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Token{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
