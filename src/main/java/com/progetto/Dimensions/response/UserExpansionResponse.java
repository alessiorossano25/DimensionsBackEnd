package com.progetto.Dimensions.response;

import java.util.ArrayList;
import java.util.List;

public class UserExpansionResponse {

        private String expansionName;
        private Long expansionId;
        private String img;

        List<CardResponse> cardResponses = new ArrayList<>();
        public UserExpansionResponse(Long expansionId, String expansionName, String img, List<CardResponse>cardResponses) {
            this.expansionId = expansionId;
            this.expansionName = expansionName;
            this.img = img;
            this.cardResponses = cardResponses;
        }

        public String getExpansionName() {
            return expansionName;
        }

        public void setExpansionName(String expansionName) {
            this.expansionName = expansionName;
        }

        public Long getExpansionId() {
            return expansionId;
        }

        public void setExpansionId(Long expansionId) {
            this.expansionId = expansionId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<CardResponse> getCardResponses() {
            return cardResponses;
        }

        public void setCardResponses(List<CardResponse> cardResponses) {
            this.cardResponses = cardResponses;
        }

        @Override
        public String toString() {
            return "ExpansionResponse{" +
                    "expansionName='" + expansionName + '\'' +
                    ", expansionId=" + expansionId +
                    ", img='" + img + '\'' +
                    ", cardResponses=" + cardResponses +
                    '}';
        }
    }


