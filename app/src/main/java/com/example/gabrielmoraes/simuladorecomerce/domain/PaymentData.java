package com.example.gabrielmoraes.simuladorecomerce.domain;

/**
 * Created by gabri on 01/04/2017.
 */

public class PaymentData {
    private String card_holder_name;
    private String card_number;
    private String creditCardMonthExpire;
    private String value;
    private String cvv;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCreditCardMonthExpire() {
        return creditCardMonthExpire;
    }

    public void setCreditCardMonthExpire(String creditCardMonthExpire) {
        this.creditCardMonthExpire = creditCardMonthExpire;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
