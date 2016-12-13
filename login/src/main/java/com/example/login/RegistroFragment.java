package com.example.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Luillo on 29/11/16.
 */

public class RegistroFragment extends Fragment implements View.OnClickListener {

    Usuario user = new Usuario();

    public EditText nombre;
    public EditText rPassword;
    public EditText password;

    public String Nombre;
    public String Password;
    public String RPassword;

    public Button btnRegistrar;


    public RegistroFragment() {
        // Required empty public constructor


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro, container, false);


        btnRegistrar = (Button) view.findViewById(R.id.btn_registrar);
        btnRegistrar.setOnClickListener(this);

        nombre = (EditText) view.findViewById(R.id.et_usuario);
        Nombre = nombre.getText() + "";

        password = (EditText) view.findViewById(R.id.et_contraseña);
        Password = password.getText() + "";

        rPassword = (EditText) view.findViewById(R.id.et_rContraseña);
        RPassword = rPassword.getText() + "";



        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_registrar) {
//        if (rPassword.getText().toString().equals(password.getText().toString())) {
            if (RPassword == Password) {
                guardarUsuario(user);

//        user.setUsuario(nombre.getText() + "");
//        user.setPassword(password.getText() + "");

                Log.i("myLog", user.toString());

            } else {
//                guardarUsuario();
                Toast.makeText(getActivity(), "Las contraseñas son diferentes", LENGTH_SHORT).show();


                // Get a Realm instance for this thread
                //   Realm realm = Realm.getDefaultInstance();
                //Regresa todos los usuarios
                // RealmResults<Usuario> allUsers = realm.where(Usuario.class).findAll();


                // for (int i = 0; i < allUsers.size(); i++) {
                //     Log.i("myLog", "User:" + allUsers.get(i));

            }
        }
    }


    private void guardarUsuario(Usuario user) {

//      RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();
//        Realm.setDefaultConfiguration(realmConfig);

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();


        //Llave que abre
        realm.beginTransaction();


//        Usuario usuario = realm.copyToRealmOrUpdate(user);
        realm.copyToRealmOrUpdate(user);

        user.setUsuario(Nombre);
        user.setPassword(Password);

        // Llave que cierra
        realm.commitTransaction();


    }

//    private void printAllUsers(){
//        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
////        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
////        Realm.setDefaultConfiguration(realmConfig);
//
//        // Get a Realm instance for this thread
//        Realm realm = Realm.getDefaultInstance();
//
//        //Regresa todos los usuarios
//        List<Usuario> allUsers =realm.where(Usuario.class).findAll();
//
//        for (int i = 0; i < allUsers.size(); i++){
//            Log.i("myLog", "User:" + allUsers.get(i));
//        }
//    }
}
