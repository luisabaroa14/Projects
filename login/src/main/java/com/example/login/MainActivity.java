package com.example.login;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView tvSignUp;
    public Button btn;

    RegistroFragment registroFragment;

    public EditText nombre;
    public EditText password;

    public String Nombre;
    public String Password;

    Usuario user = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       User user = new User("Dan", "daniel@gmail.com", 14);
//        EditText nombre;
//        EditText password;


        nombre = (EditText) findViewById(R.id.et_usuario2);
        Nombre = nombre.getText() + "";

        password = (EditText) findViewById(R.id.et_contraseña2);
        Password = password.getText() + "";

        btn = (Button) findViewById(R.id.ingresar);
        btn.setOnClickListener(this);

        tvSignUp = (ImageView) findViewById(R.id.tv_signUp);
        tvSignUp.setOnClickListener(this);

// Initialize Realm
        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();

    }


    public void cambiarFragment() {

        registroFragment = new RegistroFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_left_exit)
                .replace(R.id.frame_layout, registroFragment)
                .commit();
    }

    private void guardarUsuario(Usuario usuario) {

        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
//        Realm.setDefaultConfiguration(realmConfig);

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        //Llave que abre
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(usuario);

        usuario.setUsuario(Nombre);
        usuario.setPassword(Password);

        // Llave que cierra
        realm.commitTransaction();

        Log.i("myLog", usuario.toString());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_signUp) {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_and_out);
            tvSignUp.startAnimation(animation);

            cambiarFragment();


        }
        if (view.getId() == R.id.ingresar) {
            guardarUsuario(user);
        }

    }

}









        /* guardarUsuario(user);

        printAllUsers();
    }

    private void guardarUsuario(User user) {

        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        //Llave que abre
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(user);

        // Llave que cierra
        realm.commitTransaction();

        Log.i("myLog",user.toString());


    }*/

//    private void printAllUsers(){
//        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
//        Realm.setDefaultConfiguration(realmConfig);
//
//        // Get a Realm instance for this thread
//        Realm realm = Realm.getDefaultInstance();
//
//        //Regresa todos los usuarios
//        List<User> allUsers =realm.where(User.class).findAll();
//
//        for (int i = 0; i < allUsers.size(); i++){
//        Log.i("myLog", "User:" + allUsers.get(i));
//    }
//}

