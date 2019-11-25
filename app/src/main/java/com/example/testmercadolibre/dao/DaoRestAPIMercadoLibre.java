package com.example.testmercadolibre.dao;

import android.util.Log;

import com.example.testmercadolibre.model.ProductoBodyRespuestaAPI;
import com.example.testmercadolibre.rest.RestUtil;
import com.example.testmercadolibre.utils.ProgressBarDialog;
import com.example.testmercadolibre.utils.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoRestAPIMercadoLibre {

    private final String TAG = getClass().getSimpleName();

    public void obtenerProductos(String productoABuscar, final ResultListener<ProductoBodyRespuestaAPI> listener,int offset,int limit) {

        RestUtil restUtil = RestUtil.getInstance();

        Call<ProductoBodyRespuestaAPI> call = restUtil.llamarALaAPI().obtenerProductosAPI(productoABuscar,offset,limit);
        call.enqueue(new Callback<ProductoBodyRespuestaAPI>() {
            @Override
            public void onResponse(Call<ProductoBodyRespuestaAPI> call, Response<ProductoBodyRespuestaAPI> response) {

                if (response.isSuccessful() && response.body() != null){

                    Log.d(TAG, "RESPONSE SUCCESSFUL");
                    listener.pasarResultado(response.body());

                }


            }

            @Override
            public void onFailure(Call<ProductoBodyRespuestaAPI> call, Throwable t) {
                ProgressBarDialog.getInstance().desaparecerProgressBarDialog();
                Log.d(TAG, "RESPONSE FAILURE---- : " + t.getMessage());
            }
        });

    }


}
