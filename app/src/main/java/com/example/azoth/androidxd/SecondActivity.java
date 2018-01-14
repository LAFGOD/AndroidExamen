package com.example.azoth.androidxd;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.azoth.androidxd.Adapters.ListaCochesAdapter;
import com.example.azoth.androidxd.Adapters.ListaMensajesAdapter;
import com.example.azoth.androidxd.FBObjects.FBCoche;
import com.example.azoth.androidxd.FBObjects.Mensaje;
import com.example.milib.ListaFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    ListaFragment listaMensajesFragment, listaFragmentCoches;
    SupportMapFragment mapFragment;
    ArrayList<FBCoche> coches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        SecondActivityEvents events= new SecondActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        //listaMensajesFragment=(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListaMensajes);
        listaFragmentCoches=(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListaCoches);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
        mapFragment.getMapAsync(events);

        android.support.v4.app.FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.hide(listaFragmentCoches);
        transaction.show(mapFragment);
        transaction.commit();





        //DataHolder.instance.fireBaseAdmin.descargarYObservarRama("messages");


        /*ArrayList<String> mdatos= new ArrayList<>();
        mdatos.add("MENSAJE 1");
        mdatos.add("MENSAJE 2");
        mdatos.add("MENSAJE 3");
        mdatos.add("MENSAJE 4");

        ListaMensajesAdapter listaMensajesAdapter= new ListaMensajesAdapter(mdatos);

        listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);*/

    }
}


class SecondActivityEvents implements FireBaseAdminListener, OnMapReadyCallback{

    public SecondActivity secondActivity;
    GoogleMap mMap;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity=secondActivity;
    }

    @Override
    public void fireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

        Log.v("SecondActivity",rama+"------------"+dataSnapshot);
        if (rama.equals("messages")){
            /*GenericTypeIndicator<Map<String,Mensaje>> indicator=new GenericTypeIndicator<Map<String,Mensaje>>(){};
            Map<String,Mensaje> msgs = dataSnapshot.getValue(indicator);

            ListaMensajesAdapter listaMensajesAdapter=new ListaMensajesAdapter(new ArrayList<Mensaje>(msgs.values()));
            secondActivity.listaMensajesFragment.recyclerView.setAdapter(listaMensajesAdapter);*/

        }else if (rama.equals("Coches")){
            GenericTypeIndicator<ArrayList<FBCoche>> indicator=new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            secondActivity.coches = dataSnapshot.getValue(indicator);

            ListaCochesAdapter listaCochesAdapter=new ListaCochesAdapter(secondActivity.coches);
            secondActivity.listaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);

        }


    }

    @Override
    public void fireBaseAdmin_RegisterOK(boolean blOK) {

    }

    @Override
    public void fireBaseAdmin_LoginOK(boolean blOK) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");

    }
}
