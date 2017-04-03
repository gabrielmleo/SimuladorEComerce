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

import com.example.gabrielmoraes.simuladorecomerce.adapter.ProductsAdapter;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.Presenter;

public class MainActivity extends AppCompatActivity implements MVP.ViewOp{

    private RecyclerView mRecyclerView;
    private Toolbar mToolBar;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductsAdapter mAdapter;
    private static MVP.PresenterOp presenter;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mToolBar = (Toolbar) findViewById(R.id.tb_main);
        //this.mToolBar.setTitle(getResources().getString(R.string.main_activity_title));
        //this.mToolBar.setLogo(R.drawable.ic_github_circle_white_36dp);
        setSupportActionBar(this.mToolBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (this.presenter == null){
            this.presenter = new Presenter(this);
        }

        presenter.retrieveProducts();

        // specify an adapter (see also next example)
        mAdapter = new ProductsAdapter(this,presenter.getProducts());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == MVP.PresenterOp.CART_ACTIVITY_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == MVP.PaymentPresenterOp.PAYMENT_SUCCESS_RESULT_CODE) {
                presenter.clearCartProducts();
            }
        }
    }

    @Override
    public void updateProductsList() {
        this.mAdapter.notifyDataSetChanged();
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
}
