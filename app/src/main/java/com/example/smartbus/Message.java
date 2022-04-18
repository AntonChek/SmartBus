package com.example.smartbus;

public class Message {
    public String userName;
    public String numberPhone;

    public Message() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Message(String userName, String numberPhone) {
        this.userName = userName;
        this.numberPhone = numberPhone;


    }
}
