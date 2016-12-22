package com.example.firebase;

/**
 * Created by Luillo on 13/12/16.
 */

public class FriendlyMessage {

    private String Username;
    private String PhotoUrl;
    private String Message;
    private String Date;

    public FriendlyMessage(String Username, String PhotoUrl, String Message){
        this.Username = Username;
        this.PhotoUrl = PhotoUrl;
        this.Message = Message;
//        this.Date = Date;
    }

    public FriendlyMessage(){

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

//    public String getDate() {
//        return Date;
//    }
//
//    public void setDate(String date) {
//        Date = date;
//    }


    @Override
    public String toString() {
        return "FriendlyMessage{" +
                "Username='" + Username + '\'' +
                ", PhotoUrl='" + PhotoUrl + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}

