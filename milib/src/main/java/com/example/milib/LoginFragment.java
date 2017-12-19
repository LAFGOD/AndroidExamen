package com.example.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public EditText etUsername;
    public EditText etPassword;
    public Button btnLogin;
    public Button btnRegister;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginFragmentListener listener){
        this.listener=listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_login, container, false);

        etUsername=v.findViewById(R.id.etUsername);
        etPassword=v.findViewById(R.id.etPassword);
        btnLogin=v.findViewById(R.id.btnLogin);
        btnRegister=v.findViewById(R.id.btnRegister);


        events=new LoginFragmentEvents(this);

        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);

        return v;
    }

}

class LoginFragmentEvents implements View.OnClickListener{

    public LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment fragment){
        this.loginFragment=fragment;
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==this.loginFragment.btnLogin.getId()){
            this.loginFragment.listener.loginFragmentLoginButtonClicked(
                    this.loginFragment.etUsername.getText().toString(),
                    this.loginFragment.etPassword.getText().toString());
        } else if (view.getId()==this.loginFragment.btnRegister.getId()){
            this.loginFragment.listener.loginFragmentRegisterButtonClicked();
        }

    }
}