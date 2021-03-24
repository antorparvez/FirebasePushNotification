package com.antorparvez.firebasepushnotification.notification_service;

public class Data {
    private String Title;
    private String Message;
    private String Photo;


    public Data(String title, String message, String Photo) {
        Title = title;
        Message = message;
        this.Photo = Photo;
    }

    public Data() {
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
