package com.example.carstore;

import android.os.AsyncTask;
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
import java.util.Timer;

public  class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://www.willhaben.at/iad/gebrauchtwagen/auto/gebrauchtwagenboerse?sfId=fd2febc1-ec72-4158-bca0-0809c470d311&isNavigation=true&DEALER=1&page=1&rows=10&PRICE_TO=10000"; // Replace with the actual URL
        WebCrawler webCrawler = new WebCrawler(this);
        webCrawler.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);


        Timer t = new Timer();
        ChronJob mTask = new ChronJob(this, webCrawler);
        t.scheduleAtFixedRate(mTask, 0, 10000);
    }
    public void initiate(List<Item> items){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
    public void loadCarImage(ImageView imageView, String imgUrl) {

        Glide.with(MainActivity.this).load(imgUrl).into(imageView);
    }
}

