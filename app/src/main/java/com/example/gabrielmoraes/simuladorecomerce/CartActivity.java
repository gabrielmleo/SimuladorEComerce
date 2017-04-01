package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;

public class CartActivity extends AppCompatActivity implements MVP.CartViewOp {

    private Toolbar mToolBar;
    private RecyclerView mCartRecyclerView;
    private RecyclerView.LayoutManager mCartLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        this.mToolBar = (Toolbar) findViewById(R.id.tb_cart);
        this.mToolBar.setTitle(getResources().getString(R.string.cart_activity_name));
        setSupportActionBar(this.mToolBar);

        this.mCartRecyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        this.mCartLayoutManager = new LinearLayoutManager(this);
        this.mCartRecyclerView.setHasFixedSize(true);
        this.mCartRecyclerView.setLayoutManager(this.mCartLayoutManager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
