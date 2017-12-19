package com.example.azoth.androidxd.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.azoth.androidxd.FBObjects.FBCoche;
import com.example.azoth.androidxd.FBObjects.Mensaje;
import com.example.azoth.androidxd.R;

import java.util.ArrayList;

/**
 * Created by eduardo.lafoz on 19/12/2017.
 */

public class ListaCochesAdapter extends RecyclerView.Adapter<CocheViewHolder> {

    public ArrayList<FBCoche> coches;

    public ListaCochesAdapter(ArrayList<FBCoche> coches){
        this.coches=coches;
    }



    @Override
    public CocheViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_coche_layout,null);
        CocheViewHolder cocheViewHolder = new CocheViewHolder(view);
        return cocheViewHolder;
    }

    @Override
    public void onBindViewHolder(CocheViewHolder holder, int position) {

        //holder.textoMensaje.setText(coches.get(position).original);
        holder.tvfabricado.setText(coches.get(position).Fabricado+"");
        holder.tvmarca.setText(coches.get(position).Marca);
        holder.tvnombre.setText(coches.get(position).Nombre);
        holder.tvlat.setText(coches.get(position).lat+"");
        holder.tvlon.setText(coches.get(position).lon+"");

    }

    @Override
    public int getItemCount() {

        return coches.size();
    }
}


class CocheViewHolder extends RecyclerView.ViewHolder{

    public TextView tvfabricado;
    public TextView tvmarca;
    public TextView tvnombre;
    public TextView tvlat;
    public TextView tvlon;

    public CocheViewHolder(View itemView) {
        super(itemView);
        tvfabricado=itemView.findViewById(R.id.tvfabricado);
        tvmarca=itemView.findViewById(R.id.tvmarca);
        tvnombre=itemView.findViewById(R.id.tvnombre);
        tvlat=itemView.findViewById(R.id.tvlat);
        tvlon=itemView.findViewById(R.id.tvlon);
    }
}
