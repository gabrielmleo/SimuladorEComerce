package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.gabrielmoraes.simuladorecomerce.adapter.ProdutosAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Toolbar mToolBar;
    RecyclerView.LayoutManager mLayoutManager;
    ProdutosAdapter mAdapter;

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
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ProdutosAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }
}
