package com.example.firebase;

/**
 * Created by Luillo on 13/12/16.
 */

public class FriendlyMessage {

    private String Username;
    private String PhotoUrl;
    private String Message;
    private String Date;
    private String Time;

    public FriendlyMessage(String Username, String PhotoUrl, String Message, String Date, String Time) {
        this.Username = Username;
        this.PhotoUrl = PhotoUrl;
        this.Message = Message;
        this.Date = Date;
        this.Time = Time;
    }

    public FriendlyMessage() {

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


    @Override
    public String toString() {
        return "FriendlyMessage{" +
                "Username='" + Username + '\'' +
                ", PhotoUrl='" + PhotoUrl + '\'' +
                ", Message='" + Message + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}


