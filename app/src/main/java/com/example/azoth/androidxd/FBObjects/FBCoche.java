package com.example.azoth.androidxd.FBObjects;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardo.lafoz on 19/12/2017.
 */
@IgnoreExtraProperties
public class FBCoche {

    public int Fabricado;
    public String Marca;
    public String Nombre;
    public Double lat;
    public Double lon;

    //FUERA DE FIREBASE XD
    public Marker marker;



    public FBCoche(){

    }

    public FBCoche(int Fabricado, String Marca, String Nombre, Double lat, Double lon){

        this.Fabricado=Fabricado;
        this.Marca=Marca;
        this.Nombre=Nombre;
        this.lat=lat;
        this.lon=lon;
    }

    public void setMarker(Marker marker){
        this.marker=marker;
    }

    public Marker getMarker(){
        return marker;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Fabricado", Fabricado);
        result.put("Marca", Marca);
        result.put("Nombre", Nombre);
        result.put("lat", lat);
        result.put("lon", lon);


        return result;
    }


}
