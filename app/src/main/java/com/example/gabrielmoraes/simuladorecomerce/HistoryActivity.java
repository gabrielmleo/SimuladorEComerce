package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.gabrielmoraes.simuladorecomerce.adapter.HistoryTransactionsAdapter;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.TransactionPresenter;

public class HistoryActivity extends AppCompatActivity implements MVP.TransactionViewOp {

    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private MVP.TransactionPresenterOp mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HistoryTransactionsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mToolBar = (Toolbar) findViewById(R.id.tb_history);
        mToolBar.setTitle(getResources().getString(R.string.history_activity_name));
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (mPresenter == null){
            mPresenter = new TransactionPresenter();
        }

        mPresenter.setView(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_history);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HistoryTransactionsAdapter(this,mPresenter.getTransactionList());


    }
}
