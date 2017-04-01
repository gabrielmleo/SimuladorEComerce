package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.domain.ProductsRepositoryList;

import java.util.ArrayList;

/**
 * Created by gabri on 28/03/2017.
 */

public interface MVP {

    public interface ViewOp{
        public void updateProductsList();
        public void showCartActivity(Bundle p);
        public boolean callSuperOnOptionItemSelected(MenuItem item);
        public void addProductToCart(Product p);
    }

    public interface PresenterOp{
        public ArrayList<Product> getProducts();
        public void retrieveProducts();
        public void updateProductsList(ArrayList<Product> mList);
        public boolean onOptionsItemSelected(MenuItem item);
        public void addProductToCart(Product p);

    }

    public interface ModelOp{
        public void retrieveProducts();
        public void retrofitConfiguration();
    }

    public interface CartViewOp{

    }

    public interface CartPresenterOp{
        public void setView(CartViewOp view);
        public void setCartProducts(ArrayList<Product> list);
        public ArrayList<Product> getCartProductsList();
    }
}
