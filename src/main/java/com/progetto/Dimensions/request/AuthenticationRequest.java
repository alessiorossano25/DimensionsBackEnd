package com.progetto.Dimensions.request;

public class AuthenticationRequest {

    private String mailUsername;
    private String password;


    AuthenticationRequest (){}
    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "mailUsername='" + mailUsername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

