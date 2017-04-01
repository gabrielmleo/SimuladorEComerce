package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;

public class CartActivity extends AppCompatActivity implements MVP.CartViewOp {

    private Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        this.mToolBar = (Toolbar) findViewById(R.id.tb_cart);
        this.mToolBar.setTitle(getResources().getString(R.string.cart_activity_name));
        //this.mToolBar.setLogo(R.drawable.ic_github_circle_white_36dp);
        setSupportActionBar(this.mToolBar);
    }
}
