package com.example.azoth.androidxd.FBObjects;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Azoth on 18/12/2017.
 */
@IgnoreExtraProperties
public class Mensaje {

    public String original;

    public Mensaje(){

    }

    public Mensaje(String original){
        this.original=original;
    }


}
