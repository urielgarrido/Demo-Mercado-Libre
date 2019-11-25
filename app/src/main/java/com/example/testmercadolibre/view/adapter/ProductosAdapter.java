package com.example.testmercadolibre.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmercadolibre.R;
import com.example.testmercadolibre.model.ProductoBodyRespuestaAPI;

import java.util.ArrayList;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private Context context;
    private List<ProductoBodyRespuestaAPI.Results> productos;
    private ClickDelProductoEnRecyclerView click;


    public ProductosAdapter(Context context, ClickDelProductoEnRecyclerView click) {
        this.context = context;
        this.click = click;
        this.productos = new ArrayList<>();
    }

    public void cargarProductos(List<ProductoBodyRespuestaAPI.Results> productosACargar){
        this.productos.addAll(productosACargar);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_recycler_view_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cargarImagenDelProducto(productos.get(position));
        holder.cargarTextViewsDelProducto(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagenDelProducto;
        private TextView precioDelProducto, tituloDelProducto, condicionDelProducto;

        ViewHolder(@NonNull View v) {
            super(v);

            imagenDelProducto = v.findViewById(R.id.imageView_producto_celda_recycler_mainActivity);
            precioDelProducto = v.findViewById(R.id.textView_precio_producto_celda_recycler_mainActivity);
            tituloDelProducto = v.findViewById(R.id.textView_titulo_producto_celda_recycler_mainActivity);
            condicionDelProducto = v.findViewById(R.id.textView_condicion_producto_celda_recycler_mainActivity);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.clickProducto(productos.get(getAdapterPosition()));
                }
            });
        }

        void cargarImagenDelProducto(ProductoBodyRespuestaAPI.Results producto) {
            Glide.with(context)
                    .load(producto.thumbnail)
                    .into(imagenDelProducto);
        }

        void cargarTextViewsDelProducto(ProductoBodyRespuestaAPI.Results results) {
            int indexDelNameDelAttibute = 0;

            precioDelProducto.setText("$" + results.price);
            tituloDelProducto.setText(results.title);

            for (int i = 0; i < results.attributes.size(); i++) {

                if (results.attributes.get(i).name.equalsIgnoreCase("Condición del ítem")) {
                    indexDelNameDelAttibute = i;
                    break;
                }
            }

            condicionDelProducto.setText(results.attributes.get(indexDelNameDelAttibute).value_name);
        }
    }


    public interface ClickDelProductoEnRecyclerView {
        void clickProducto(ProductoBodyRespuestaAPI.Results producto);
    }
}
