package com.example.gabrielmoraes.simuladorecomerce.domain;


/**
 * Created by gabri on 02/04/2017.
 */

public class PaymentTransaction {

    String transactionValue;
    String creditCardLastDigits;
    String transactionDate;
    String creditCardOwnerName;

    public PaymentTransaction(){

    }

    public PaymentTransaction(String value, String cardDigits,String date, String owner){
        transactionValue = value;
        creditCardLastDigits = cardDigits;
        transactionDate = date;
        creditCardOwnerName = owner;

    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public String getCreditCardLastDigits() {
        return creditCardLastDigits;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getCreditCardOwnerName() {
        return creditCardOwnerName;
    }
}
