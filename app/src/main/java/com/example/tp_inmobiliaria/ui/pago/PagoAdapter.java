package com.example.tp_inmobiliaria.ui.pago;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.models.Pago;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder> {

    private List<Pago> lista;
    private Context context;

    public PagoAdapter(List<Pago> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public PagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pago, parent, false);
        return new PagoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoViewHolder holder, int position) {
        Pago p = lista.get(position);

        holder.tvNumeroPago.setText("N° de Pago: " + p.getIdPago());
        holder.tvFechaPago.setText("Fecha: " + formatoFecha(p.getFechaPago()));
        holder.tvImporte.setText("Importe: $" + p.getMonto());
        holder.tvDetalle.setText("Detalle: " + p.getDetalle());
        holder.tvEstado.setText("Estado: " + (p.isEstado() ? "Pagado" : "Pendiente"));

    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public static class PagoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumeroPago, tvFechaPago, tvImporte, tvDetalle, tvEstado;

        public PagoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumeroPago = itemView.findViewById(R.id.tvNumeroPago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
            tvImporte = itemView.findViewById(R.id.tvImporte);
            tvDetalle = itemView.findViewById(R.id.tvDetalle);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }
    }

    public String formatoFecha(String fechaIso) {
        if (fechaIso == null || fechaIso.trim().isEmpty()) {
            return "Sin fecha";
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaIso);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return fecha.format(formatter);
        } catch (DateTimeParseException e) {
            return fechaIso;
        }
    }
}
