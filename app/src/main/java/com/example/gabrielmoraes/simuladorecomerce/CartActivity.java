package com.example.gabrielmoraes.simuladorecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.gabrielmoraes.simuladorecomerce.adapter.CartProductsAdapter;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.mvp.CartPresenter;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements MVP.CartViewOp {

    private Toolbar mToolBar;
    private RecyclerView mCartRecyclerView;
    private RecyclerView.LayoutManager mCartLayoutManager;
    private MVP.CartPresenterOp mCartPresenter;
    private CartProductsAdapter mCartAdapter;
    private RelativeLayout mEmptyCartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mEmptyCartList = (RelativeLayout) findViewById(R.id.empty_cart_view);

        this.mToolBar = (Toolbar) findViewById(R.id.tb_cart);
        this.mToolBar.setTitle(getResources().getString(R.string.cart_activity_name));
        setSupportActionBar(this.mToolBar);

        this.mCartRecyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        this.mCartLayoutManager = new LinearLayoutManager(this);
        this.mCartRecyclerView.setHasFixedSize(true);
        this.mCartRecyclerView.setLayoutManager(this.mCartLayoutManager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mCartPresenter ==null){
            mCartPresenter = new CartPresenter();
        }

        mCartPresenter.setView(this);
        if (getIntent() != null){
            ArrayList<Product> l = getIntent().getParcelableArrayListExtra(MVP.PresenterOp.BUNDLE_KEY);
            mCartPresenter.setCartProducts(l);
            mEmptyCartList.setVisibility(View.GONE);

        }

        mCartAdapter = new CartProductsAdapter(this,mCartPresenter.getCartProductsList());
        mCartRecyclerView.setAdapter(mCartAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_tb_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mCartPresenter.onOptionsItemSelected(item);

    }

    @Override
    public void showPaymentActivity(Bundle b) {
        Intent i = new Intent(this,PaymentActivity.class);
        i.putExtras(b);
        startActivity(i);
    }

    @Override
    public boolean callSuperOnOptionItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
