package com.example.gabrielmoraes.simuladorecomerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabrielmoraes.simuladorecomerce.R;
import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentTransaction;
import com.example.gabrielmoraes.simuladorecomerce.mvp.MVP;
import com.example.gabrielmoraes.simuladorecomerce.util.Util;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by gabri on 02/04/2017.
 */

public class HistoryTransactionsAdapter extends RecyclerView.Adapter<HistoryTransactionsAdapter.TransactionViewHolder> {

    private List<PaymentTransaction> mTransactionsList;
    private MVP.TransactionViewOp mView;


    public HistoryTransactionsAdapter(MVP.TransactionViewOp viewOp, List<PaymentTransaction> list){
        mView = viewOp;
        mTransactionsList = list;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cell,parent,false);
        TransactionViewHolder viewHolder = new TransactionViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.setValues(mTransactionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTransactionsList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{

        private TextView transactionOwner;
        private TextView transactionCreditCard;
        private TextView transactionValue;
        private TextView transactionDate;

        public TransactionViewHolder(View itemView) {
            super(itemView);

            transactionOwner = (TextView) itemView.findViewById(R.id.transaction_owner);
            transactionCreditCard = (TextView) itemView.findViewById(R.id.transaction_credit_card);
            transactionValue = (TextView) itemView.findViewById(R.id.transaction_value);
            transactionDate = (TextView) itemView.findViewById(R.id.transaction_date);
        }


        public void setValues(PaymentTransaction pTransaction){
            transactionOwner.setText(pTransaction.creditCardOwnerName);
            transactionCreditCard.setText(mView.getHiddenMask()+pTransaction.creditCardLastDigits);
            transactionValue.setText(Util.currencyFormater(pTransaction.transactionValue));
            transactionDate.setText(pTransaction.transactionDate);
        }
    }
}
