package com.example.gabrielmoraes.simuladorecomerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielmoraes.simuladorecomerce.R;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gabri on 01/04/2017.
 */

public class CartProductsAdapter extends RecyclerView.Adapter<CartProductsAdapter.CartViewHolder> {

    private ArrayList<Product> mProducts;


    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_cell,parent,false);
        CartViewHolder viewHolder = new CartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        holder.setValues(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        private ImageView mCartProductImage;
        private TextView mCartProductName;
        private TextView mCartProductSeller;

        public CartViewHolder(View itemView) {
            super(itemView);

            this.mCartProductImage = (ImageView) itemView.findViewById(R.id.cart_product_image);
            this.mCartProductName = (TextView) itemView.findViewById(R.id.cart_product_name);
            this.mCartProductSeller = (TextView) itemView.findViewById(R.id.cart_seller_product_name);
        }

        public void setValues(Product p){
            this.mCartProductName.setText(p.getTitle());
            this.mCartProductSeller.setText(p.getSeller());
            Picasso.with(this.mCartProductImage.getContext()).load(p.getThumbnailHd()).into(this.mCartProductImage);
        }
    }
}
