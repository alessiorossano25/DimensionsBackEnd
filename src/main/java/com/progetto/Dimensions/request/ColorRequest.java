package com.progetto.Dimensions.request;

public class ColorRequest {

    private Long idCard;

    private boolean IsColor;


    public ColorRequest(Long idCard, boolean isColor) {
        this.idCard = idCard;
        IsColor = isColor;
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public boolean isColor() {
        return IsColor;
    }

    public void setColor(boolean color) {
        IsColor = color;
    }

    @Override
    public String toString() {
        return "ColorRequest{" +
                "idCard=" + idCard +
                ", IsColor=" + IsColor +
                '}';
    }
}
