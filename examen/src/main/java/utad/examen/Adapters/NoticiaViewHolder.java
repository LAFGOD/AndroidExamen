package utad.examen.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utad.examen.R;

/**
 * Created by eduardo.lafoz on 19/12/2017.
 */

public class NoticiaViewHolder extends RecyclerView.ViewHolder{

    public ListaNoticiasAdapterListener listener;
    public TextView tvtitulo;
    public TextView tvcuerpo;
    public ImageView ivnoticia;

    public void setListener(ListaNoticiasAdapterListener listener){
        this.listener=listener;
    }


    public NoticiaViewHolder(View itemView) {
        super(itemView);
        tvtitulo=itemView.findViewById(R.id.tvtitulo);
        tvcuerpo=itemView.findViewById(R.id.tvcuerpo);
        ivnoticia=itemView.findViewById(R.id.ivnoticia);


        NoticiaViewHolderEvents events= new NoticiaViewHolderEvents(this);
        itemView.setOnClickListener(events);
    }
}

class NoticiaViewHolderEvents implements View.OnClickListener{

    NoticiaViewHolder noticiaViewHolder;

    public NoticiaViewHolderEvents(NoticiaViewHolder noticiaViewHolder){
        this.noticiaViewHolder=noticiaViewHolder;
    }

    @Override
    public void onClick(View v) {

        noticiaViewHolder.listener.ListaNoticiasAdapterCeldaClick(noticiaViewHolder);

    }
}
