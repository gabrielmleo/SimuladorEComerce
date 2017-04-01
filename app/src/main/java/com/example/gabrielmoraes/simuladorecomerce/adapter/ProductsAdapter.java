package com.example.gabrielmoraes.simuladorecomerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielmoraes.simuladorecomerce.MainActivity;
import com.example.gabrielmoraes.simuladorecomerce.R;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.domain.ProductsRepositoryList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gabri on 28/03/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private MainActivity mActivity;
    private ArrayList<Product> mProductsList;


    public ProductsAdapter(MainActivity activity, ArrayList<Product> list){

        this.mActivity = activity;
        this.mProductsList = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cell,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setDados(this.mProductsList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mProductsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mProductNameTextView;
        public TextView mPriceTextView;
        public TextView mSellerNameTextView;
        public ImageView mProductPhotoImageView;

        public ViewHolder(View v) {
            super(v);
            mProductPhotoImageView = (ImageView)v.findViewById(R.id.produto_image);
            mProductNameTextView = (TextView) v.findViewById(R.id.nome_produto_text);
            mSellerNameTextView = (TextView)v.findViewById(R.id.nome_loja_text);
            mPriceTextView = (TextView) v.findViewById(R.id.preco_text);
        }

        public void setDados(Product p){
            this.mProductNameTextView.setText(p.getTitle());
            this.mSellerNameTextView.setText(p.getSeller());
            this.mPriceTextView.setText(p.getPrice());
            Picasso.with(mProductPhotoImageView.getContext()).load(p.getThumbnailHd()).into(mProductPhotoImageView);
        }
    }

}
