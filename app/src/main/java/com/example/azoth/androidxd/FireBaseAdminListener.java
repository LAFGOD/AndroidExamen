package com.example.azoth.androidxd;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Azoth on 17/12/2017.
 */

public interface FireBaseAdminListener {

    public void fireBaseAdmin_RegisterOK(boolean blOK);
    public void fireBaseAdmin_LoginOK(boolean blOK);
    public void fireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot);
}
