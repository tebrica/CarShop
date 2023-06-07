package com.example.carstore;

public class Item {

    String name;
    String email;
    MainActivity MainActivity;
    public Item(String name, String email, MainActivity MainActivity) {
        this.name = name;
        this.email = email;
        this.MainActivity = MainActivity;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public MainActivity getMainActivity() {
        return MainActivity;
    }
}
