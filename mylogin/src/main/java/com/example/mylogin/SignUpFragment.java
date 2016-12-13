package com.example.mylogin;


import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mylogin.models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncCredentials;

import static io.realm.Realm.getDefaultInstance;


public class SignUpFragment extends Fragment implements View.OnClickListener{


    EditText Username;
    EditText Password;
    EditText rPassword;
    Button SignUp;

   public Realm realm;

    SyncCredentials myCredentials = SyncCredentials.usernamePassword("luisAbaroa", "labaroasordo12", true);


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Realm.init(getContext());
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

        Username = (EditText) view.findViewById(R.id.et_username);
        Password = (EditText) view.findViewById(R.id.et_password);
        rPassword = (EditText) view.findViewById(R.id.et_RPassword);
        SignUp = (Button) view.findViewById(R.id.btn_sign_up);
        SignUp.setOnClickListener(this);

        realm = Realm.getDefaultInstance();


        actualizarUsuarios();

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_sign_up){

            if (Password.getText().toString().equals(rPassword.getText().toString())) {
                guardarUsuario(Username.getText().toString(), Password.getText().toString());

//            Log.v("myLog",Username.getText().toString());
//            Log.v("myLog",Password.getText().toString());



        }else {
                Toast.makeText(getContext(), "Passwords are different", Toast.LENGTH_LONG).show();
            }

    }}

    private void actualizarUsuarios() {

// Or alternatively do the same all at once (the "Fluent interface"):
        RealmResults<User> userResults = realm.where(User.class).findAll();
        for (User user: userResults ) {
            Log.v("myLog",user.toString() + " " );
        }
    }


    private void guardarUsuario(final String username , final String password) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                User user = bgRealm.createObject(User.class);
                user.setUsername(username);
                user.setPassword(password);


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
//                Log.v("myLog", "User " + username + " has signed in");

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("myLog",error.getMessage());

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }
}
