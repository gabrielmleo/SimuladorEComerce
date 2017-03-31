package com.example.gabrielmoraes.simuladorecomerce.mvp;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.domain.ProductsRepositoryList;

import java.util.ArrayList;

/**
 * Created by gabri on 29/03/2017.
 */

public class Presenter implements MVP.PresenterOp {

    private MVP.ModelOp mModelOp;
    private MVP.ViewOp mViewOp;
    private ArrayList<Product> mProductList = new ArrayList<Product>();


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


}
