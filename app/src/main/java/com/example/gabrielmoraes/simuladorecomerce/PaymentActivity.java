package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.PaymentPresenter;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private MVP.PaymentPresenterOp mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mToolBar = (Toolbar)findViewById(R.id.tb_payment);
        this.mToolBar.setTitle(getResources().getString(R.string.payment_activity_name));
        setSupportActionBar(this.mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (mPresenter == null){
            mPresenter = new PaymentPresenter();
        }

        if (getIntent() != null){
            ArrayList<Product> l = getIntent().getParcelableArrayListExtra(MVP.CartPresenterOp.BUNDLE_PAYMENT_KEY);
            mPresenter.setCartProducts(l);
        }

    }





}
