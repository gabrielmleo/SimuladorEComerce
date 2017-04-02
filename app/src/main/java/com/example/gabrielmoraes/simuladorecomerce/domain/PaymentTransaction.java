package com.example.gabrielmoraes.simuladorecomerce.domain;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by gabri on 02/04/2017.
 */
@Table(name = "PaymentsTransaction")
public class PaymentTransaction extends Model {

    @Column(name = "TransactionValue")
    public String transactionValue;

    @Column(name = "CreditCardLastDigits")
    public String creditCardLastDigits;

    @Column(name = "TransactionDate")
    public String transactionDate;

    @Column(name = "CreditCardOwnerName")
    public String creditCardOwnerName;

    public PaymentTransaction(){
        super();
    }

    public PaymentTransaction(String value, String cardDigits,String date, String owner){
        super();
        transactionValue = value;
        creditCardLastDigits = cardDigits;
        transactionDate = date;
        creditCardOwnerName = owner;

    }
    public static List<PaymentTransaction> getAll() {
        return new Select()
                .from(PaymentTransaction.class)
                .execute();
    }
}
