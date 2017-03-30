package com.example.gabrielmoraes.simuladorecomerce.network;

import com.example.gabrielmoraes.simuladorecomerce.domain.ProdutosRepositoryList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gabri on 29/03/2017.
 */

public interface ProdutoRepositoryService {

    public static final String BASE_URL = "https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/";

    @GET("master/products.json")
    Call<ProdutosRepositoryList> listProdutos();
}
