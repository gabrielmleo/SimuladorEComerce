package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.gabrielmoraes.simuladorecomerce.adapter.ProductsAdapter;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.domain.ProductsRepositoryList;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.Presenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MVP.ViewOp{

    private RecyclerView mRecyclerView;
    private Toolbar mToolBar;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductsAdapter mAdapter;
    private static MVP.PresenterOp presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mToolBar = (Toolbar) findViewById(R.id.tb_main);
        //this.mToolBar.setTitle(getResources().getString(R.string.main_activity_title));
        //this.mToolBar.setLogo(R.drawable.ic_github_circle_white_36dp);
        setSupportActionBar(this.mToolBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
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
    public void updateProductsList() {
        this.mAdapter.notifyDataSetChanged();
    }
}
