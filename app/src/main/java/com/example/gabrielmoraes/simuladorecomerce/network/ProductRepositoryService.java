package com.example.gabrielmoraes.simuladorecomerce.network;

import com.example.gabrielmoraes.simuladorecomerce.domain.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gabri on 29/03/2017.
 */

public interface ProductRepositoryService {

    String BASE_URL = "https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/master/";

    @GET("products.json")
    Call<ArrayList<Product>> listProducts();
}
