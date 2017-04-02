package com.example.gabrielmoraes.simuladorecomerce.network;

import com.example.gabrielmoraes.simuladorecomerce.domain.PaymentData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by gabri on 01/04/2017.
 */

public interface ProductPaymentService {

    String BASE_URL = "http://ecommerce.apiblueprint.org/";

    @POST("payment")
    Call<PaymentData> newPayment(@Body PaymentData payment);
}
