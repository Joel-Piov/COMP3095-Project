package com.assignment2.COMP3095.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Card {

    private long clientId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String cardType;

    @NotNull
    @Size(min=2,max=32,message="Please enter first and last name of the card holder")
    private String cardName;

    @NotNull
    @Size(min=16,max=20,message="Please enter the full card number (16 digits).")
    @Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$",
            message="Please enter a valid Credit Card number.")
    private String cardNumber;

    @NotNull
    @Size(min=6,max=12,message="Password should be between 6 and 12 characters.")
    private String expCode;

    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpCode() {
        return expCode;
    }
    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }
}
