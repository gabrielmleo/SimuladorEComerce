package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.view.View;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 02/04/2017.
 */

public class TransactionPresenter implements MVP.TransactionPresenterOp {

    private MVP.TransactionViewOp mView;
    private MVP.TransactionModelOp mModel;

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
    @Override
    public void checkEmptyList() {
        if (mModel.getTransactionList().size()!=0){
            mView.setEmptyListVisibility(View.GONE);
        }
        else{
            mView.setEmptyListVisibility(View.VISIBLE);
        }
    }
}
