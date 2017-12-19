package com.example.azoth.androidxd.FBObjects;

import com.google.firebase.database.IgnoreExtraProperties;

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



    public FBCoche(){

    }

    public FBCoche(int Fabricado, String Marca, String Nombre, Double lat, Double lon){

        this.Fabricado=Fabricado;
        this.Marca=Marca;
        this.Nombre=Nombre;
        this.lat=lat;
        this.lon=lon;
    }


}
