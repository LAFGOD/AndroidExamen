package com.example.azoth.androidxd;

/**
 * Created by Azoth on 18/12/2017.
 */

public class DataHolder {

    public static DataHolder instance=new DataHolder();

    public FireBaseAdmin fireBaseAdmin;

    public DataHolder()
    {
        fireBaseAdmin=new FireBaseAdmin();
    }
}
