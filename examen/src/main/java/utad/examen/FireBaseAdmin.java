package utad.examen;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by eduardo.lafoz on 19/12/2017.
 */

public class FireBaseAdmin {

    private FirebaseAuth mAuth;
    public FireBaseAdminListener listener;
    public FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRefRaiz;


    public FireBaseAdmin(){

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRefRaiz=database.getReference();
    }

    public void setListener(FireBaseAdminListener listener){
        this.listener=listener;
    }

    public void registerConEmailYPassword(String email, String pass, Activity activity){

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FireBaseAdmin", "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (task.isSuccessful()) {
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            listener.fireBaseAdmin_RegisterOK(true);
                        }
                        else{
                            listener.fireBaseAdmin_RegisterOK(false);
                        }


                    }
                });

    }

    public void loginConEmailYPassword(String email, String pass, Activity activity){

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FireBaseAdmin", "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (task.isSuccessful()) {
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            listener.fireBaseAdmin_LoginOK(true);
                        }
                        else{
                            listener.fireBaseAdmin_LoginOK(false);
                        }


                    }
                });

    }

    public void descargarYObservarRama(final String rama){

        DatabaseReference refRama= myRefRaiz.child(rama);

        // Read from the database
        refRama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listener.fireBaseAdmin_RamaDescargada(rama,dataSnapshot);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                listener.fireBaseAdmin_RamaDescargada(rama,null);
            }
        });

    }

}
