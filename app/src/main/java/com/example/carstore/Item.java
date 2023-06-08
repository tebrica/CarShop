package com.example.carstore;

import java.util.Date;

public class Item {

    String name;
    String email;
    String imgUrl;
    MainActivity MainActivity;
    private Date time;
    public Item(String name, String email, String imgUrl,MainActivity MainActivity, Date time) {
        this.name = name;
        this.email = email;
        this.MainActivity = MainActivity;
        this.imgUrl = imgUrl;
        this.setTime(time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MainActivity getMainActivity() {
        return MainActivity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
