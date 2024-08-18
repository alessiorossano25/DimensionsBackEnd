package com.progetto.Dimensions.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
    @Table(name = "Cards")
    public class Card {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotEmpty
        private String type;

        @NotEmpty
        private String name;

        private int numbercard;

        @NotEmpty
        private String img;

        @ManyToOne
        @JoinColumn(name = "Expansions_id")
        private Expansion expansion;

        @ManyToMany(mappedBy = "cards")
        private Set<User> users = new HashSet<>();

        public Card (){}
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

        public int getNumbercard() {
            return numbercard;
        }

        public void setNumbercard(int numbercard) {
            this.numbercard = numbercard;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Expansion getExpansion() {
            return expansion;
        }

        public void setExpansion(Expansion expansion) {
            this.expansion = expansion;
        }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", numberCard=" + numbercard +
                ", img='" + img + '\'' +
                ", expansion=" + expansion +
                ", users=" + users +
                '}';
    }
}
