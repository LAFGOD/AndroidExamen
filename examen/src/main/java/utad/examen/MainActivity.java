package utad.examen;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.LoginFragment;
import com.example.milib.LoginFragmentListener;
import com.example.milib.RegisterFragment;
import com.example.milib.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;


public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment= (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);
        registerFragment= (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRegister);
        MainActivityEvents mainActivityEvents=new MainActivityEvents(this);



        //fireBaseAdmin=new FireBaseAdmin();

        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();


        //DataHolder.instance.fireBaseAdmin.loginConEmailYPassword("test1@test1.com", "1234567890",this);



    }
}

class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FireBaseAdminListener{
    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;

    }

    @Override
    public void loginFragmentLoginButtonClicked(String sUser, String sPassword) {

        DataHolder.instance.fireBaseAdmin.loginConEmailYPassword(sUser,sPassword,mainActivity);

    }

    @Override
    public void loginFragmentRegisterButtonClicked() {

        FragmentTransaction transaction= mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(mainActivity.loginFragment);
        transaction.show(mainActivity.registerFragment);
        transaction.commit();

    }

    @Override
    public void registerFragmentAceptarClicked(String sUser, String sPassword) {

        DataHolder.instance.fireBaseAdmin.registerConEmailYPassword(sUser,sPassword,mainActivity);

    }

    @Override
    public void registerFragmentCancelarClicked() {

        FragmentTransaction transaction= mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(mainActivity.loginFragment);
        transaction.hide(mainActivity.registerFragment);
        transaction.commit();

    }

    @Override
    public void fireBaseAdmin_RegisterOK(boolean blOK) {
        Log.v("MAINACTIVITYEVENTS","RESULTADO DE REGISTER "+blOK);
        /*if (blOK){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }*/
    }

    @Override
    public void fireBaseAdmin_LoginOK(boolean blOK) {
        Log.v("MAINACTIVITYEVENTS","RESULTADO DE REGISTER "+blOK);
        /*if (blOK){
            Intent intent = new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }*/

    }

    @Override
    public void fireBaseAdmin_RamaDescargada(String rama, DataSnapshot dataSnapshot) {

    }

}

