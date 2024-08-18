package com.progetto.Dimensions.response;

import com.progetto.Dimensions.model.Expansion;

public class AllCardResponse {
    private Long id;

    private String name;

    private int numberCard;

    private String img;

    private Long expansionId;

    private String expansionName;



    public AllCardResponse(Long id, String name, int numberCard, String img, Long expansionId, String expansionName) {
        this.id = id;
        this.name = name;
        this.numberCard = numberCard;
        this.img = img;
        this.expansionId = expansionId;
        this.expansionName = expansionName;

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

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getExpansionId() {
        return expansionId;
    }

    public void setExpansionId(Long expansionId) {
        this.expansionId = expansionId;
    }

    public String getExpansionName() {
        return expansionName;
    }

    public void setExpansionName(String expansionName) {
        this.expansionName = expansionName;
    }

    @Override
    public String toString() {
        return "AllCardResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberCard=" + numberCard +
                ", img='" + img + '\'' +
                ", expansionId=" + expansionId +
                ", expansionName='" + expansionName + '\'' +
                '}';
    }
}
