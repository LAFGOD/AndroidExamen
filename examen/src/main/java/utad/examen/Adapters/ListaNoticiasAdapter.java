package utad.examen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import utad.examen.FBObjects.FBNoticia;
import utad.examen.R;

/**
 * Created by eduardo.lafoz on 19/12/2017.
 */

public class ListaNoticiasAdapter extends RecyclerView.Adapter<NoticiaViewHolder> {

    public ArrayList<FBNoticia> noticias;
    public Context mContext;
    public ListaNoticiasAdapterListener listener;


    public ListaNoticiasAdapter(ArrayList<FBNoticia> noticias, Context mContext){
        this.noticias=noticias;
        this.mContext=mContext;
    }


    public void setListener(ListaNoticiasAdapterListener listener){
        this.listener=listener;
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
        holder.setListener(this.listener);

        Glide.with(mContext).load(noticias.get(position).Img)
                .into(holder.ivnoticia);

    }

    @Override
    public int getItemCount() {

        return noticias.size();
    }
}




