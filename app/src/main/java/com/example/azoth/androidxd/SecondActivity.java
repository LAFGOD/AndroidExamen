package com.example.azoth.androidxd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.azoth.androidxd.Adapters.ListaCochesAdapter;
import com.example.azoth.androidxd.FBObjects.FBCoche;
import com.example.milib.DetallesFragment;
import com.example.milib.GPSAdmin.GPSTracker;
import com.example.milib.ListaFragment;
import com.example.milib.asyntasks.HttpAsyncTask;
import com.example.milib.asyntasks.HttpJsonAsyncTask;
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
import java.util.HashMap;
import java.util.Map;

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
            FBCoche fbCoche = new FBCoche(2017,"Coche Test","Renault",gpsTracker.getLatitude(),gpsTracker.getLongitude());
            //DataHolder.instance.fireBaseAdmin.insertarEnRama("/Coches/5",fbCoche.toMap());

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/Coches/5",fbCoche.toMap()); //PRIMER INSERT
            String key=DataHolder.instance.fireBaseAdmin.generarKeySobreRama("/CochesConID/");
            childUpdates.put("/CochesConID/" + key, fbCoche.toMap()); //SEGUNDO INSERT
            DataHolder.instance.fireBaseAdmin.insertarEnMultiRamas(childUpdates);
        }
        else {
            gpsTracker.showSettingsAlert();
        }

        /*HttpAsyncTask httpAsyncTask=new HttpAsyncTask();
        httpAsyncTask.execute("http://www.worldbestlearningcenter.com/tips/img-files/android_stop_asynctask.jpg");*/

        HttpJsonAsyncTask httpJsonAsyncTask=new HttpJsonAsyncTask();
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",40.4165,-3.52256,"metric","a37ce897b2c637531a86e89b71f6efa1" +
                "");
        httpJsonAsyncTask.execute(url);

        HttpJsonAsyncTask httpJsonAsyncTask2=new HttpJsonAsyncTask();
        String url2 = String.format("http://10.0.2.2/EDU/xdhaha/login.php");
        httpJsonAsyncTask.execute(url2);

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
