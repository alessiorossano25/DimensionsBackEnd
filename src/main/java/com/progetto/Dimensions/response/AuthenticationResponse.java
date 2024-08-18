package com.progetto.Dimensions.response;

public class AuthenticationResponse {

    private String token;
    private Long expiration;
    private String username;

    private String mail;

    private String img;

    private String name;

    private String surname;

    public AuthenticationResponse(String token, Long expiration, String username, String mail, String img, String name, String surname) {
        this.token = token;
        this.expiration = expiration;
        this.username = username;
        this.mail = mail;
        this.img = img;
        this.name = name;
        this.surname = surname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                ", expiration=" + expiration +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + '}';
    }
}
