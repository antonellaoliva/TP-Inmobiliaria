package com.example.tp_inmobiliaria.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.models.Inmueble;
import com.example.tp_inmobiliaria.request.ApiClient;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder> {

    private List<Inmueble> lista;
    private Context context;

    public ContratoAdapter(List<Inmueble> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ContratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contrato, parent, false);
        return new ContratoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);

        holder.tvDetalle.setText(inmueble.getDireccion());
        Glide.with(context)
                .load(ApiClient.URLBASE + inmueble.getImagen())
                .placeholder(R.drawable.baseline_home_24)
                .into(holder.ivInmueble);


        holder.btnVer.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            Navigation.findNavController(v).navigate(R.id.detalleContratoFragment, bundle);
        });


    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public static class ContratoViewHolder extends RecyclerView.ViewHolder {

        ImageView ivInmueble;
        TextView tvDetalle;
        Button btnVer;

        public ContratoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivInmueble = itemView.findViewById(R.id.ivInmueble);
            tvDetalle = itemView.findViewById(R.id.tvDireccion);
            btnVer = itemView.findViewById(R.id.btnVer);
        }
    }
}
