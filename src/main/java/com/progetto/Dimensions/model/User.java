package com.progetto.Dimensions.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String username;
    @Email
    @NotEmpty
    private String mail;
    @NotEmpty
    private String password;

    private String img;

    @ManyToMany
    @JoinTable(name = "users_cards", joinColumns = @JoinColumn(name= "users_id"), inverseJoinColumns = @JoinColumn(name= "cards_id"))
    private Set<Card> cards = new HashSet<>();


    public User (){}

    public User(String name, String surname, String username, String mail, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
    public User(String name, String surname, String username, String mail, String password,String img) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.img = img;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", img='" + img + '\'' +
                ", cards=" + cards +
                '}';
    }
}


