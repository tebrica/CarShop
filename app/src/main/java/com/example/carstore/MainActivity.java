package com.example.carstore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity {

    JSONObject JO;
    private static UiThread uiThread;
    JavascriptActivity javascriptActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //javascriptActivity = new JavascriptActivity();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("John wick","john.wick@email.com",this));
        items.add(new Item("Robert j","robert.j@email.com",this));
        items.add(new Item("James Gunn","james.gunn@email.com",this));
        items.add(new Item("Ricky tales","rickey.tales@email.com",this));
        items.add(new Item("Micky mose","mickey.mouse@email.com",this));
        items.add(new Item("Pick War","pick.war@email.com",this));
        items.add(new Item("Leg piece","leg.piece@email.com",this));
        items.add(new Item("Apple Mac","apple.mac@email.com",this));
        items.add(new Item("John wick","john.wick@email.com",this));
        items.add(new Item("Robert j","robert.j@email.com",this));
        items.add(new Item("James Gunn","james.gunn@email.com",this));
        items.add(new Item("Ricky tales","rickey.tales@email.com",this));
        items.add(new Item("Micky mose","mickey.mouse@email.com",this));
        items.add(new Item("Pick War","pick.war@email.com",this));
        items.add(new Item("Leg piece","leg.piece@email.com",this));
        items.add(new Item("Apple Mac","apple.mac@email.com",this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

    }
    public void loadDogImage(ImageView imageView) {

        RequestQueue volleyQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://dog.ceo/api/breeds/image/random";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,

                (Response.Listener<JSONObject>) response -> {
                    String dogImageUrl;
                    try {
                        dogImageUrl = response.getString("message");
                        Glide.with(MainActivity.this).load(dogImageUrl).into(imageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JO = response;
                },

                (Response.ErrorListener) error -> {
                    Toast.makeText(MainActivity.this, "Some error occurred! Cannot fetch dog image", Toast.LENGTH_LONG).show();

                    Log.e("MainActivity", "loadDogImage error: ${error.localizedMessage}");
                }
        );

        volleyQueue.add(jsonObjectRequest);
    }
}

