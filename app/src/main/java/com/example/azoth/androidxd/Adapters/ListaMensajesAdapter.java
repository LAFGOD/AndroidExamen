package com.example.azoth.androidxd.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.azoth.androidxd.FBObjects.Mensaje;
import com.example.azoth.androidxd.R;

import java.util.ArrayList;

/**
 * Created by Azoth on 18/12/2017.
 */

public class ListaMensajesAdapter extends RecyclerView.Adapter<MensajeViewHolder> {

    public ArrayList<Mensaje> mensajes;

    public ListaMensajesAdapter(ArrayList<Mensaje> mensajes){
        this.mensajes=mensajes;
    }



    @Override
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_mensaje_layout,null);
        MensajeViewHolder mensajeViewHolder = new MensajeViewHolder(view);
        return mensajeViewHolder;
    }

    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {

        holder.textoMensaje.setText(mensajes.get(position).original);

    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }
}


class MensajeViewHolder extends RecyclerView.ViewHolder{

    public TextView textoMensaje;

    public MensajeViewHolder(View itemView) {
        super(itemView);
        textoMensaje=itemView.findViewById(R.id.textoMensaje);
    }
}