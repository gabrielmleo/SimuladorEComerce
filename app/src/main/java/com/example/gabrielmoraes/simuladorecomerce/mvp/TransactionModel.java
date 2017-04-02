package com.example.gabrielmoraes.simuladorecomerce.mvp;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentTransaction;

import java.util.List;

/**
 * Created by gabri on 02/04/2017.
 */

public class TransactionModel implements MVP.TransactionModelOp {

    private MVP.TransactionPresenterOp mPresenter;

    public TransactionModel(MVP.TransactionPresenterOp presenter){
        mPresenter = presenter;
    }

    @Override
    public List<PaymentTransaction> getTransactionList() {

        List<PaymentTransaction> transactionsList = PaymentTransaction.listAll(PaymentTransaction.class);

        return transactionsList;

    }
}
