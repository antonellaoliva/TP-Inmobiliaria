package com.example.tp_inmobiliaria.ui.inquilinos;

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

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.InmuebleViewHolder> {
    private List<Inmueble> lista;
    private Context context;

    public InquilinoAdapter(List<Inmueble> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inquilino, parent, false);
        return new InmuebleViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);

        holder.tvDireccion.setText(inmueble.getDireccion());
        Glide.with(context)
                .load(ApiClient.URLBASE + inmueble.getImagen())
                .placeholder(R.drawable.baseline_home_24)
                .error("null")
                .into(holder.ivImagen);

        holder.btnVerDetalle.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("idInmueble", inmueble.getIdInmueble());
            Navigation.findNavController(v).navigate(R.id.detalleInquilinoFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }


    public static class InmuebleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvDireccion;
        Button btnVerDetalle;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.imgInquilino);
            tvDireccion = itemView.findViewById(R.id.tvDetalle);
            btnVerDetalle = itemView.findViewById(R.id.btnVer);
        }
    }
}

