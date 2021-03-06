package utad.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

import utad.examen.Adapters.ListaNoticiasAdapter;
import utad.examen.Adapters.ListaNoticiasAdapterListener;
import utad.examen.Adapters.NoticiaViewHolder;
import utad.examen.FBObjects.FBNoticia;

public class SecondActivity extends AppCompatActivity {

    ListaFragment listaNoticiasFragment;

    ListaNoticiasAdapter listaNoticiasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        SecondActivityEvents events= new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        listaNoticiasFragment=(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListaNoticias);


        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Noticias");



    }
}

class SecondActivityEvents implements FireBaseAdminListener, ListaNoticiasAdapterListener{

    public SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity=secondActivity;
    }

    @Override
    public void fireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

        GenericTypeIndicator<ArrayList<FBNoticia>> indicator=new GenericTypeIndicator<ArrayList<FBNoticia>>(){};
        ArrayList<FBNoticia> noticias = dataSnapshot.getValue(indicator);

        secondActivity.listaNoticiasAdapter=new ListaNoticiasAdapter(noticias, secondActivity);
        secondActivity.listaNoticiasFragment.recyclerView.setAdapter(secondActivity.listaNoticiasAdapter);
        secondActivity.listaNoticiasAdapter.setListener(this);

    }

    @Override
    public void fireBaseAdmin_RegisterOK(boolean blOK) {

    }

    @Override
    public void fireBaseAdmin_LoginOK(boolean blOK) {

    }

    @Override
    public void ListaNoticiasAdapterCeldaClick(NoticiaViewHolder celdaHolder) {

    }
}
