package com.example.gabrielmoraes.simuladorecomerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielmoraes.simuladorecomerce.R;

/**
 * Created by gabri on 28/03/2017.
 */

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mNomeProdutoTextView;
        public TextView mPrecoTextView;
        public TextView mNomeVendedorTextView;
        public ImageView mFotoProdutoImageView;

        public ViewHolder(View v) {
            super(v);
            mFotoProdutoImageView = (ImageView)v.findViewById(R.id.produto_image);
            mNomeProdutoTextView = (TextView) v.findViewById(R.id.nome_produto_text);
            mNomeVendedorTextView = (TextView)v.findViewById(R.id.nome_loja_text);
            mPrecoTextView = (TextView) v.findViewById(R.id.preco_text);
        }
    }

}
