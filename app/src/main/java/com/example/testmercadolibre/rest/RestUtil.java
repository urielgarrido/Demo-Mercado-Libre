package com.example.testmercadolibre.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestUtil {

    private Retrofit retrofit;

    private static RestUtil instancia;
    private APIMercadoLibre apiMercadoLibre;

    public static synchronized RestUtil getInstance(){
        if (instancia == null){
            return instancia = new RestUtil();
        }else return instancia;
    }


    private RestUtil(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/sites/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient.build())
                .build();
    }

    public APIMercadoLibre llamarALaAPI(){
        return apiMercadoLibre = retrofit.create(APIMercadoLibre.class);
    }


}
