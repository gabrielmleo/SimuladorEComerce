package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.content.Context;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;

import java.util.ArrayList;

/**
 * Created by gabri on 01/04/2017.
 */

public class PaymentPresenter implements MVP.PaymentPresenterOp {

    private MVP.PaymentViewOp mView;
    private ArrayList<Product> mProductList = new ArrayList<>();
    private Context mContext;


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
    public void setContext(Context c) {
        mContext = c;

    }
}
