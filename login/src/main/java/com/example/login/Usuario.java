package com.example.login;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Luillo on 29/11/16.
 */
public class Usuario extends RealmObject {


    @PrimaryKey
    public String usuario;
    public String password;


    public Usuario(){

    }

    public Usuario(String usuario, String password) {
        this.usuario=usuario;
        this.password=password;
    }


    public String getusuario(){
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
