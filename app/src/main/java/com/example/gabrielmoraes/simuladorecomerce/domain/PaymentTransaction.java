package com.example.gabrielmoraes.simuladorecomerce.domain;

import com.orm.SugarRecord;

/**
 * Created by gabri on 02/04/2017.
 */

public class PaymentTransaction extends SugarRecord {

    private String transactionValue;
    private String creditCardLastDigits;
    private String transactionDate;
    private String creditCardOwnerName;

    public String getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getCreditCardLastDigits() {
        return creditCardLastDigits;
    }

    public void setCreditCardLastDigits(String creditCardLastDigits) {
        this.creditCardLastDigits = creditCardLastDigits;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCreditCardOwnerName() {
        return creditCardOwnerName;
    }

    public void setCreditCardOwnerName(String creditCardOwnerName) {
        this.creditCardOwnerName = creditCardOwnerName;
    }
}
