package utad.examen.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import utad.examen.FBObjects.FBNoticia;
import utad.examen.R;

/**
 * Created by eduardo.lafoz on 19/12/2017.
 */

public class ListaNoticiasAdapter extends RecyclerView.Adapter<NoticiaViewHolder> {

    public ArrayList<FBNoticia> noticias;

    public ListaNoticiasAdapter(ArrayList<FBNoticia> noticias){
        this.noticias=noticias;
    }



    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_noticia_layout,null);
        NoticiaViewHolder noticiaViewHolder = new NoticiaViewHolder(view);
        return noticiaViewHolder;
    }

    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {

        //holder.textoMensaje.setText(coches.get(position).original);
        holder.tvtitulo.setText(noticias.get(position).Titulo);
        holder.tvcuerpo.setText(noticias.get(position).Cuerpo);


    }

    @Override
    public int getItemCount() {

        return noticias.size();
    }
}


class NoticiaViewHolder extends RecyclerView.ViewHolder{

    public TextView tvtitulo;
    public TextView tvcuerpo;


    public NoticiaViewHolder(View itemView) {
        super(itemView);
        tvtitulo=itemView.findViewById(R.id.tvtitulo);
        tvcuerpo=itemView.findViewById(R.id.tvcuerpo);

    }




}
