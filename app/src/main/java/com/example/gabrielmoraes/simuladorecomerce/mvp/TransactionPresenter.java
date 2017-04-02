package com.example.gabrielmoraes.simuladorecomerce.mvp;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 02/04/2017.
 */

public class TransactionPresenter implements MVP.TransactionPresenterOp {

    private MVP.TransactionViewOp mView;
    private MVP.TransactionModelOp mModel;
    private List<PaymentTransaction> mTransactionList = new ArrayList<>();

    public TransactionPresenter(){
        mModel = new TransactionModel(this);
    }

    @Override
    public List<PaymentTransaction> getTransactionList() {
        return mModel.getTransactionList();
    }

    @Override
    public void setView(MVP.TransactionViewOp viewOp) {
        mView = viewOp;
    }
}
