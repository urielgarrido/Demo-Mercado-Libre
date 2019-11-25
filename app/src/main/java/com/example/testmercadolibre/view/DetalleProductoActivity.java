package com.example.testmercadolibre.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testmercadolibre.R;
import com.example.testmercadolibre.model.ProductoBodyRespuestaAPI;

public class DetalleProductoActivity extends AppCompatActivity {

    public static final String PRODUCTO = "productoSeleccionado";

    private ProductoBodyRespuestaAPI.Results productoSeleccionado;

    private ImageView imageViewProducto;
    private TextView textViewNombreDelProducto,textViewPrecioDelProducto,textViewDireccionDelProducto;
    private LinearLayout linearLayoutTextViewsConDetalleDelProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        findViews();
        obtenerProductoSeleccionado();
    }

    private void findViews() {

        imageViewProducto = findViewById(R.id.imageView_producto_detalleAvtivity);

        textViewNombreDelProducto = findViewById(R.id.textView_nombre_producto_detalleActivity);
        textViewPrecioDelProducto = findViewById(R.id.textView_precio_producto_detalleActivity);
        textViewDireccionDelProducto = findViewById(R.id.textView_direccion_producto_detalleActivity);

        linearLayoutTextViewsConDetalleDelProducto = findViewById(R.id.linearLayout_contenedor_detalle_textViews_detalleActivity);

    }

    private void obtenerProductoSeleccionado() {
        Intent intentRecivido = getIntent();
        Bundle bundleRecivido = intentRecivido.getExtras();

        if (bundleRecivido != null) {
            productoSeleccionado = (ProductoBodyRespuestaAPI.Results) bundleRecivido.getSerializable(PRODUCTO);

            cargarViewConLosDatosDelProducto();
        }
    }

    private void cargarViewConLosDatosDelProducto() {
        Glide.with(this)
                .load(productoSeleccionado.thumbnail)
                .into(imageViewProducto);


        textViewNombreDelProducto.setText(productoSeleccionado.title);
        textViewPrecioDelProducto.setText("$"+productoSeleccionado.price);
        textViewDireccionDelProducto.setText(productoSeleccionado.address.state_name+" , "+productoSeleccionado.address.city_name);


        for (ProductoBodyRespuestaAPI.Attributes attribute:productoSeleccionado.attributes){
            TextView textView = new TextView(this);
            textView.setText(attribute.name+": "+attribute.value_name);
            linearLayoutTextViewsConDetalleDelProducto.addView(textView);
        }

    }
}
