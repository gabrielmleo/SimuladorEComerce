package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.gabrielmoraes.simuladorecomerce.R;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;

import java.util.ArrayList;

/**
 * Created by gabri on 01/04/2017.
 */

public class CartPresenter implements MVP.CartPresenterOp {

    private ArrayList<Product> mCartProductsList = new ArrayList<>();
    private MVP.CartViewOp mView;

    @Override
    public void setView(MVP.CartViewOp view) {
        mView = view;
    }


    @Override
    public void setCartProducts(ArrayList<Product> list) {
        mCartProductsList.clear();
        mCartProductsList.addAll(list);
    }

    @Override
    public ArrayList<Product> getCartProductsList() {
        return mCartProductsList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.payment_menu_button:
                Bundle b = new Bundle();
                b.putParcelableArrayList(BUNDLE_PAYMENT_KEY,mCartProductsList);
                mView.showPaymentActivity(b);
                return true;
            default:
                return mView.callSuperOnOptionItemSelected(item);
        }
    }
}
