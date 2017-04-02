package com.example.gabrielmoraes.simuladorecomerce.mvp;

import android.util.Log;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentData;
import com.example.gabrielmoraes.simuladorecomerce.domain.Product;
import com.example.gabrielmoraes.simuladorecomerce.network.ProductPaymentService;
import com.example.gabrielmoraes.simuladorecomerce.network.ProductRepositoryService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gabri on 02/04/2017.
 */

public class PaymentModel implements MVP.PaymentModelOp {

    private PaymentData mPaymentData;
    private MVP.PaymentPresenterOp mPresenterOp;
    private Retrofit retrofit;
    private String creditCardOwner;
    private String creditCardNumber;
    private String creditCardCvv;
    private String creditCardMonth;
    private String creditCardYear;

    public PaymentModel(MVP.PaymentPresenterOp presenterOp){
        mPresenterOp = presenterOp;
        mPaymentData = new PaymentData();
        retrofitConfiguration();
    }


    @Override
    public void requestPayment() {

        ProductPaymentService service = this.retrofit.create(ProductPaymentService.class);
        Call<PaymentData> requestRepositoryList = service.newPayment(mPaymentData);

        requestRepositoryList.enqueue(new Callback<PaymentData>() {
            @Override
            public void onResponse(Call<PaymentData> call, Response<PaymentData> response) {
                if (!response.isSuccessful()){
                    //mPresenterOp.updateProductsList(response.body());
                }
                else{
                    //mPresenterOp.updateProductsList(response.body());
                }
            }

            @Override
            public void onFailure(Call<PaymentData> call, Throwable t) {

                //Log.i("AIAIAIAI0","AIAIAIAIAOOOOOPPP");
            }
        });

    }

    @Override
    public void retrofitConfiguration() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ProductPaymentService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void setCreditCardOwnerName(String name) {
        creditCardOwner = name;
    }

    @Override
    public void setCreditCardNumber(String number) {
        creditCardNumber = number;
    }

    @Override
    public void setCreditCardCvv(String cvv) {
        creditCardCvv = cvv;
    }

    @Override
    public void setCreditCardMonth(String month) {
        creditCardMonth = month;
    }

    @Override
    public void setCreditCardYear(String year) {
        creditCardYear = year;
    }
}
