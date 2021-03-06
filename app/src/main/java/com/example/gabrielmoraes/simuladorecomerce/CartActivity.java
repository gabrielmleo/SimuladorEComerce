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
    public static String PRODUCTS_CART_KEY = "PRODUCTS_CART_KEY";
    public ArrayList<Product> mCartProducts = new ArrayList<>();


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

        retrieveProducts(savedInstanceState);


        mCartAdapter = new CartProductsAdapter(this,mCartPresenter.getCartProductsList());
        mCartRecyclerView.setAdapter(mCartAdapter);
    }

    public void retrieveProducts(Bundle savedInstanceState){
        if (savedInstanceState!=null){
            mCartProducts = savedInstanceState.getParcelableArrayList(PRODUCTS_CART_KEY);
        }else{
            if (getIntent() != null){
                mCartProducts = getIntent().getParcelableArrayListExtra(MVP.PresenterOp.BUNDLE_KEY);

            }
        }

        mCartPresenter.setCartProducts(mCartProducts);
        mCartPresenter.checkEmptyList();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(PRODUCTS_CART_KEY, mCartPresenter.getCartProductsList());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_tb_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu!=null){
            if (mCartPresenter.mustShowMenuItem()){
                menu.getItem(0).setVisible(true);
            }else{
                menu.getItem(0).setVisible(false);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mCartPresenter.onOptionsItemSelected(item);

    }

    @Override
    public void showPaymentActivity(Bundle b) {
        Intent i = new Intent(this,PaymentActivity.class);
        i.putExtras(b);
        //startActivity(i);
        startActivityForResult(i,MVP.PaymentPresenterOp.PAYMENT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == MVP.PaymentPresenterOp.PAYMENT_ACTIVITY_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == MVP.PaymentPresenterOp.PAYMENT_SUCCESS_RESULT_CODE) {
                mCartPresenter.clearData();
                setResult(MVP.PaymentPresenterOp.PAYMENT_SUCCESS_RESULT_CODE);
                finish();
            }
        }
    }

    @Override
    public boolean callSuperOnOptionItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setEmptyListVisibility(int i) {
        mEmptyCartList.setVisibility(i);
    }

    @Override
    public void updateMenuItem() {
        invalidateOptionsMenu();
    }

    @Override
    public void updateCartItens() {
        mCartAdapter.notifyDataSetChanged();
        mCartPresenter.checkEmptyList();

    }

    @Override
    public void onBackPressed() {
        setResultIfCartIsEmpty();
        finish();
        super.onBackPressed();
    }

    @Override
    public void back() {
        setResultIfCartIsEmpty();
        finish();
    }

    public void setResultIfCartIsEmpty(){
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(MVP.CartPresenterOp.BUNDLE_CART_LIST,mCartPresenter.getCartProductsList());
            setResult(MVP.CartPresenterOp.CART_EMPRY_RESULT_CODE,returnIntent);

    }
}
