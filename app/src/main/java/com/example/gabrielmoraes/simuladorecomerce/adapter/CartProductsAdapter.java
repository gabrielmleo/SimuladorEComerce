package com.example.gabrielmoraes.simuladorecomerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielmoraes.simuladorecomerce.CartActivity;
import com.example.gabrielmoraes.simuladorecomerce.R;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gabri on 01/04/2017.
 */

public class CartProductsAdapter extends RecyclerView.Adapter<CartProductsAdapter.CartViewHolder> {

    private ArrayList<Product> mProducts;
    private CartActivity mCartActivity;

    public CartProductsAdapter(CartActivity activity, ArrayList<Product> p){
        mProducts = p;
        mCartActivity = activity;
    }


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

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mCartProductImage;
        private TextView mCartProductName;
        private TextView mCartProductSeller;
        private TextView mCartProductPrice;
        private Button mCartDeleteProduct;

        public CartViewHolder(View itemView) {
            super(itemView);

            mCartProductImage = (ImageView) itemView.findViewById(R.id.cart_product_image);
            mCartProductName = (TextView) itemView.findViewById(R.id.cart_product_name);
            mCartProductSeller = (TextView) itemView.findViewById(R.id.cart_seller_product_name);
            mCartProductPrice = (TextView) itemView.findViewById(R.id.cart_product_price);
            mCartDeleteProduct = (Button) itemView.findViewById(R.id.delete_product);
            mCartDeleteProduct.setOnClickListener(this);
        }

        public void setValues(Product p){
            mCartProductName.setText(p.getTitle());
            mCartProductSeller.setText(p.getSeller());
            mCartProductPrice.setText(Util.currencyFormater(p.getPrice()));
            Picasso.with(this.mCartProductImage.getContext()).load(p.getThumbnailHd()).into(this.mCartProductImage);
        }

        @Override
        public void onClick(View v) {
            mProducts.remove(getAdapterPosition());
            mCartActivity.updateCartItens();
        }
    }
}
