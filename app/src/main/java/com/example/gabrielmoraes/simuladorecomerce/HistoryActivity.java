package com.example.gabrielmoraes.simuladorecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.gabrielmoraes.simuladorecomerce.adapter.HistoryTransactionsAdapter;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.mvp.TransactionPresenter;

public class HistoryActivity extends AppCompatActivity implements MVP.TransactionViewOp {

    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private MVP.TransactionPresenterOp mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HistoryTransactionsAdapter mAdapter;
    private RelativeLayout emptyHistoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        emptyHistoryView = (RelativeLayout) findViewById(R.id.empty_history_view);



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
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setEmptyListVisibility(int i) {
        emptyHistoryView.setVisibility(i);
    }
}
