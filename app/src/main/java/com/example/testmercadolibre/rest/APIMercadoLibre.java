package com.example.testmercadolibre.rest;

import com.example.testmercadolibre.model.ProductoBodyRespuestaAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIMercadoLibre {


    @GET("MLA/search")
    @Headers("Content-Type: application/json")
    Call<ProductoBodyRespuestaAPI> obtenerProductosAPI(@Query("q") String productoABuscar,@Query("offset") int offset,@Query("limit") int limit);




}
