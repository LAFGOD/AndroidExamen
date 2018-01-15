package com.example.azoth.androidxd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.azoth.androidxd.Adapters.ListaCochesAdapter;
import com.example.azoth.androidxd.FBObjects.FBCoche;
import com.example.milib.DetallesFragment;
import com.example.milib.GPSAdmin.GPSTracker;
import com.example.milib.ListaFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListaFragment listaMensajesFragment, listaFragmentCoches;
    SupportMapFragment mapFragment;
    DetallesFragment detallesFragment;



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

        detallesFragment=(DetallesFragment) getSupportFragmentManager().findFragmentById(R.id.detallesFragment);

        android.support.v4.app.FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.hide(listaFragmentCoches);
        transaction.hide(detallesFragment);
        transaction.show(mapFragment);
        transaction.commit();

        GPSTracker gpsTracker=new GPSTracker(this);
        if (gpsTracker.canGetLocation()){

        }
        else {
            gpsTracker.showSettingsAlert();
        }



    }
}


class SecondActivityEvents implements FireBaseAdminListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    public SecondActivity secondActivity;
    GoogleMap mMap;
    ArrayList<FBCoche> coches;

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
            quitarViejosPines();
            GenericTypeIndicator<ArrayList<FBCoche>> indicator=new GenericTypeIndicator<ArrayList<FBCoche>>(){};
            coches = dataSnapshot.getValue(indicator);

            ListaCochesAdapter listaCochesAdapter=new ListaCochesAdapter(coches);
            secondActivity.listaFragmentCoches.recyclerView.setAdapter(listaCochesAdapter);
            agregarPinesCoches();


        }


    }

    public void agregarPinesCoches(){
        for (int i=0;i<coches.size();i++){
            FBCoche cocheTemp=coches.get(i);
            LatLng cochePos = new LatLng(cocheTemp.lat, cocheTemp.lon);
            MarkerOptions markerOptions=new MarkerOptions();
            markerOptions.position(cochePos);
            markerOptions.title(cocheTemp.Nombre);
            if (mMap!=null){
                Marker marker=mMap.addMarker(markerOptions);
                marker.setTag(cocheTemp);
                cocheTemp.setMarker(marker);
                if (i==0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cochePos,8));
                }
            }

        }
    }


    public void quitarViejosPines(){
        if (coches!=null){
            for (int i=0;i<coches.size();i++){
                FBCoche cocheTemp=coches.get(i);
                if (cocheTemp.getMarker()!=null){
                    cocheTemp.getMarker().remove();
                }
            }
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
        mMap.setOnMarkerClickListener(this);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        DataHolder.instance.fireBaseAdmin.descargarYObservarRama("Coches");

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        FBCoche coche= (FBCoche)marker.getTag();

        secondActivity.detallesFragment.txtNom.setText(coche.Nombre);
        secondActivity.detallesFragment.txtMar.setText(coche.Marca);
        secondActivity.detallesFragment.txtFab.setText(coche.Fabricado+"");


        android.support.v4.app.FragmentTransaction transaction= secondActivity.getSupportFragmentManager().beginTransaction();
        //transaction.hide(secondActivity.listaFragmentCoches);
        transaction.show(secondActivity.detallesFragment);
        //transaction.hide(secondActivity.mapFragment);
        transaction.commit();

        return false;
    }
}
