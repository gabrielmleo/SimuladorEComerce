package com.example.gabrielmoraes.simuladorecomerce.mvp;

import com.example.gabrielmoraes.simuladorecomerce.network.ProdutoRepositoryService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gabri on 29/03/2017.
 */

public class Model implements MVP.ModelOp {

    private MVP.PresenterOp mPresenterOp;
    private Retrofit retrofit;



    public Model(MVP.PresenterOp mPresenterOp){
        this.mPresenterOp = mPresenterOp;
        this.retrofitConfiguration();
    }

    @Override
    public void retrieveProdutos() {

    }

    @Override
    public void retrofitConfiguration() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(ProdutoRepositoryService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
