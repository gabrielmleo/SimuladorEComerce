package com.example.gabrielmoraes.simuladorecomerce.mvp;

import com.example.gabrielmoraes.simuladorecomerce.domain.Produto;

import java.util.ArrayList;

/**
 * Created by gabri on 29/03/2017.
 */

public class Presenter implements MVP.PresenterOp {

    private MVP.ModelOp mModelOp;
    private MVP.ViewOp mViewOp;
    private ArrayList<Produto> mProdutoList;


    public Presenter (MVP.ViewOp mViewOp){
        this.mViewOp = mViewOp;
        this.mModelOp = new Model(this);
    }
}
