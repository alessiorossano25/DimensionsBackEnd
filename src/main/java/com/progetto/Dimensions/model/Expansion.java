package com.progetto.Dimensions.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

import java.util.Set;

@Entity
@Table(name = "Expansions")
public class Expansion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String img;

    @OneToMany(mappedBy = "expansion")
    private Set<Card> cards;


    public Expansion (){}


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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Expansion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
