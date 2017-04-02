package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.content.Context;

import com.example.gabrielmoraes.simuladorecomerce.PaymentActivity;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;

import java.util.ArrayList;

/**
 * Created by gabri on 01/04/2017.
 */

public class PaymentPresenter implements MVP.PaymentPresenterOp {

    private MVP.PaymentViewOp mView;
    private MVP.PaymentModelOp mModel;
    private ArrayList<Product> mProductList = new ArrayList<>();


    public PaymentPresenter(){
        this.mModel = new PaymentModel(this);
    }


    @Override
    public void setView(MVP.PaymentViewOp view) {
        mView = view;

    }

    @Override
    public void setCartProducts(ArrayList<Product> list) {
        mProductList.clear();
        mProductList.addAll(list);
    }

    @Override
    public String getAmountValue() {
        int i = 0;

        for (Product p : mProductList){

            i = i+ Integer.valueOf(p.getPrice());
        }

        return Integer.toString(i);
    }

    @Override
    public void confirmPayment() {

        boolean error = false;
        String errorMessage = "Verifique os campos e tente novamente";
        if ((mView.getCreditCardNumber().length() !=16) || mView.getCreditCardCvv().length() !=3 ){
            error = true;
        }
        if ((mView.getCreditCardMonth().length()!=2) || (mView.getCreditCardYear().length()!=4)){
            error = true;
        }

        if (error){
            mView.showToast(errorMessage);
        }

        mModel.setCreditCardCvv(mView.getCreditCardCvv());
        mModel.setCreditCardOwnerName(mView.getCreditCardOwnerName());
        mModel.setCreditCardMonth(mView.getCreditCardMonth());
        mModel.setCreditCardYear(mView.getCreditCardYear());
        mModel.setCreditCardNumber(mView.getCreditCardNumber());

        mModel.requestPayment();

    }

}
