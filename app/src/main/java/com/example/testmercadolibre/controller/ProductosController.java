package com.example.testmercadolibre.controller;

import com.example.testmercadolibre.dao.DaoRestAPIMercadoLibre;
import com.example.testmercadolibre.model.ProductoBodyRespuestaAPI;
import com.example.testmercadolibre.utils.ResultListener;

public class ProductosController {

    private DaoRestAPIMercadoLibre dao;
    private int offsetProductosPaginacion = 0;
    private int limitProductosPaginacion = 50;

    public void obtenerProductosDeLaAPI(String productoABuscar,int offset,int limit, final ResultListener<ProductoBodyRespuestaAPI> listenerDeLaActivity) {

        dao = new DaoRestAPIMercadoLibre();
        dao.obtenerProductos(productoABuscar, new ResultListener<ProductoBodyRespuestaAPI>() {
            @Override
            public void pasarResultado(ProductoBodyRespuestaAPI resultado) {
                listenerDeLaActivity.pasarResultado(resultado);
            }
        }, offset, limit);

    }

    public int obtenerSiguientesProductos() {

        return offsetProductosPaginacion += limitProductosPaginacion;

    }


}
