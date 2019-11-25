package com.example.testmercadolibre.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testmercadolibre.R;
import com.example.testmercadolibre.controller.ProductosController;
import com.example.testmercadolibre.model.ProductoBodyRespuestaAPI;
import com.example.testmercadolibre.utils.ProgressBarDialog;
import com.example.testmercadolibre.utils.ResultListener;
import com.example.testmercadolibre.view.adapter.ProductosAdapter;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private EditText buscadorEditText;
    private RecyclerView productosRecycler;
    private ProductosAdapter adapter;
    private ProductosController controller;
    private int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        controller = new ProductosController();
    }

    private void findViews() {
        buscadorEditText = findViewById(R.id.editText_buscador_mainActivity);

        buscadorEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean action = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    ProgressBarDialog.getInstance().mostrarProgressBarDialog(MainActivity.this).show();

                    obtenerProductosDeMercadoLibre();
                    action = true;
                }
                return action;
            }
        });

        productosRecycler = findViewById(R.id.recyclerView_productos_mainActivity);
        final LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        productosRecycler.setLayoutManager(linearLayoutManager);
        productosRecycler.setHasFixedSize(true);

        productosRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Integer posicionActual = linearLayoutManager.findLastVisibleItemPosition();
                int ultimaCelda = linearLayoutManager.getItemCount();

                Log.i(TAG,posicionActual.toString());

                if (posicionActual.equals(ultimaCelda - 9)) {
                    obtenerProductosDeMercadoLibre();
                    Toast.makeText(MainActivity.this, "Obteniendo más productos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapter = new ProductosAdapter(this, new ProductosAdapter.ClickDelProductoEnRecyclerView() {
            @Override
            public void clickProducto(ProductoBodyRespuestaAPI.Results producto) {
                Intent intent = new Intent(MainActivity.this,DetalleProductoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetalleProductoActivity.PRODUCTO, producto);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        productosRecycler.setAdapter(adapter);

    }

    private void obtenerProductosDeMercadoLibre() {

        controller.obtenerProductosDeLaAPI(buscadorEditText.getText().toString(),offset,50, new ResultListener<ProductoBodyRespuestaAPI>() {
            @Override
            public void pasarResultado(ProductoBodyRespuestaAPI resultado) {
                ProgressBarDialog.getInstance().desaparecerProgressBarDialog();
                if (!resultado.results.isEmpty()){
                    adapter.cargarProductos(resultado.results);
                    offset = controller.obtenerSiguientesProductos();
                }
            }
        });

    }


}
