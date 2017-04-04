package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.util.Log;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.network.ProductRepositoryService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    public void retrieveProducts() {
        ProductRepositoryService service = this.retrofit.create(ProductRepositoryService.class);
        Call<ArrayList<Product>> requestRepositoryList = service.listProducts();

        requestRepositoryList.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (!response.isSuccessful()){
                    //mPresenterOp.updateProductsList(response.body());
                }
                else{
                    mPresenterOp.updateProductsList(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                Log.i("AIAIAIAI0","AIAIAIAIAOOOOOPPP");
            }
        });

    }

    @Override
    public void retrofitConfiguration() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(ProductRepositoryService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void renewedList(ArrayList<Product> list) {
        ArrayList<Product> renewedList = new ArrayList<>();
        renewedList.addAll(list);

        mPresenterOp.updateProductsList(renewedList);
    }
}
