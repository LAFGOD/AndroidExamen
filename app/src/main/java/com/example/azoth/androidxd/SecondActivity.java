package com.example.azoth.androidxd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.azoth.androidxd.Adapters.ListaCochesAdapter;
import com.example.azoth.androidxd.Adapters.ListaMensajesAdapter;
import com.example.azoth.androidxd.FBObjects.FBCoche;
import com.example.azoth.androidxd.FBObjects.Mensaje;
import com.example.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    ListaFragment listaMensajesFragment, listaFragmentCoches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        SecondActivityEvents events= new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        listaMensajesFragment=(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListaMensajes);
        listaFragmentCoches=(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListaCoches);

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("messages");
        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");

        /*ArrayList<String> mdatos= new ArrayList<>();
        mdatos.add("MENSAJE 1");
        mdatos.add("MENSAJE 2");
        mdatos.add("MENSAJE 3");
        mdatos.add("MENSAJE 4");

        ListaMensajesAdapter listaMensajesAdapter= new ListaMensajesAdapter(mdatos);

        listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);*/

    }
}


class SecondActivityEvents implements FireBaseAdminListener{

    public SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity=secondActivity;
    }

    @Override
    public void fireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

        Log.v("SecondActivity",rama+"------------"+dataSnapshot);
        if (rama.equals("messages")){
            GenericTypeIndicator<Map<String,Mensaje>> indicator=new GenericTypeIndicator<Map<String,Mensaje>>(){};
            Map<String,Mensaje> msgs = dataSnapshot.getValue(indicator);

            ListaMensajesAdapter listaMensajesAdapter=new ListaMensajesAdapter(new ArrayList<Mensaje>(msgs.values()));
            secondActivity.listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);

        }else if (rama.equals("Coches")){
            GenericTypeIndicator<ArrayList<FBCoche>> indicator=new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            ArrayList<FBCoche> coches = dataSnapshot.getValue(indicator);

            ListaCochesAdapter listaCochesAdapter=new ListaCochesAdapter(coches);
            secondActivity.listaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);

        }


    }

    @Override
    public void fireBaseAdmin_RegisterOK(boolean blOK) {

    }

    @Override
    public void fireBaseAdmin_LoginOK(boolean blOK) {

    }
}
