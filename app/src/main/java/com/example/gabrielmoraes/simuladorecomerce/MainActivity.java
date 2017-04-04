package com.example.gabrielmoraes.simuladorecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gabrielmoraes.simuladorecomerce.adapter.ProductsAdapter;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.Presenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MVP.ViewOp{

    private RecyclerView mRecyclerView;
    private Toolbar mToolBar;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductsAdapter mAdapter;
    private static MVP.PresenterOp presenter;
    private ProgressBar mProgressCircular;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mToolBar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(this.mToolBar);

        mProgressCircular = (ProgressBar)findViewById(R.id.progress_circular);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (this.presenter == null){
            this.presenter = new Presenter(this);
        }

        presenter.retrieveProducts(savedInstanceState);

        mAdapter = new ProductsAdapter(this,presenter.getProducts());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MVP.PresenterOp.PRODUCTS_KEY, presenter.getProducts());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_tb_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MVP.PresenterOp.CART_ACTIVITY_REQUEST_CODE) {
            if (resultCode == MVP.PaymentPresenterOp.PAYMENT_SUCCESS_RESULT_CODE) {
                presenter.clearCartProducts();
                showToast(getResources().getString(R.string.payment_success));
            }else if(resultCode == MVP.CartPresenterOp.CART_EMPRY_RESULT_CODE){
                if (data!=null){
                    ArrayList<Product> array = data.getParcelableArrayListExtra(MVP.CartPresenterOp.BUNDLE_CART_LIST);
                    presenter.addCartProducts(array);

                }

            }
        }
    }

    @Override
    public void updateProductsList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCartActivity(Bundle b) {
        Intent i = new Intent(this,CartActivity.class);
        i.putExtras(b);
        startActivityForResult(i,MVP.PresenterOp.CART_ACTIVITY_REQUEST_CODE);

    }

    @Override
    public void showHistoryActivity() {
        startActivity(new Intent(this,HistoryActivity.class));
    }

    @Override
    public boolean callSuperOnOptionItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addProductToCart(Product p) {
        presenter.addProductToCart(p);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showItemAddedMessage() {
        Toast.makeText(this,getResources().getString(R.string.item__added_to_cart),Toast.LENGTH_SHORT).show();
    }



}
