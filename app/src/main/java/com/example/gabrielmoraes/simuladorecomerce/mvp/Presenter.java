package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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
    private Context mContext;


    public Presenter (MVP.ViewOp mViewOp){
        this.mViewOp = mViewOp;
        this.mModelOp = new Model(this);
    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.mProductList;
    }

    @Override
    public void retrieveProducts(Bundle saveInstanceState) {
        if (saveInstanceState!=null){
            ArrayList<Product> products = saveInstanceState.getParcelableArrayList(MVP.PresenterOp.PRODUCTS_KEY);
            mModelOp.renewedList(products);
        }else{
            mModelOp.retrieveProducts();
        }

    }

    @Override
    public void updateProductsList(ArrayList<Product> mList){
        mProductList.clear();
        mProductList.addAll(mList);
        mViewOp.updateProductsList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_menu_button:
                Bundle b = new Bundle();
                b.putParcelableArrayList(BUNDLE_KEY,mCartProductList);
                mViewOp.showCartActivity(b);
                return true;

            case R.id.history_menu_button:
                mViewOp.showHistoryActivity();
                return true;
            default:
                return mViewOp.callSuperOnOptionItemSelected(item);
        }
    }

    @Override
    public void addProductToCart(Product p) {
        mCartProductList.add(p);
        mViewOp.showItemAddedMessage();
    }

    @Override
    public void clearCartProducts() {
        mCartProductList.clear();
    }

    @Override
    public void addCartProducts(ArrayList<Product> products) {
        clearCartProducts();
        mCartProductList.addAll(products);
    }

    @Override
    public void connectionHasFailed() {
        mViewOp.showToast(mContext.getResources().getString(R.string.fail_to_download_products));
    }

    @Override
    public void setContext(Context con) {
        mContext = con;
    }


}
