package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gabrielmoraes.simuladorecomerce.R;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;

import java.util.ArrayList;

/**
 * Created by gabri on 29/03/2017.
 */

public class Presenter implements MVP.PresenterOp {

    private MVP.ModelOp mModelOp;
    private MVP.ViewOp mViewOp;
    private ArrayList<Product> mProductList = new ArrayList<>();
    private ArrayList<Product> mCartProductList = new ArrayList<>();


    public Presenter (MVP.ViewOp mViewOp){
        this.mViewOp = mViewOp;
        this.mModelOp = new Model(this);
    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.mProductList;
    }

    @Override
    public void retrieveProducts() {
        this.mModelOp.retrieveProducts();
    }

    @Override
    public void updateProductsList(ArrayList<Product> mList){
        this.mProductList.clear();
        this.mProductList.addAll(mList);
        this.mViewOp.updateProductsList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_menu_button:
                Bundle b = new Bundle();
                b.putParcelableArrayList(BUNDLE_KEY,mCartProductList);
                mViewOp.showCartActivity(b);
                return true;
            default:
                return mViewOp.callSuperOnOptionItemSelected(item);
        }
    }

    @Override
    public void addProductToCart(Product p) {
        mCartProductList.add(p);
    }


}
