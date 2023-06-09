package com.example.carstore;

import static com.example.carstore.R.id.osv10;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Timer;

public  class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Timer t;
    ChronJob mTask;
    WebCrawler webCrawler;
    public Boolean call = true;
    EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://www.willhaben.at/iad/gebrauchtwagen/auto/gebrauchtwagenboerse?sfId=fd2febc1-ec72-4158-bca0-0809c470d311&isNavigation=true&DEALER=1&page=1&rows=10&PRICE_TO=10000"; // Replace with the actual URL
        webCrawler = new WebCrawler(this);
        webCrawler.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);


        t = new Timer();
        mTask = new ChronJob(this, webCrawler);
        //t.scheduleAtFixedRate(mTask, 0, 20000);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void initiate(List<Item> items){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
    public void loadCarImage(ImageView imageView, String imgUrl) {

        Glide.with(MainActivity.this).load(imgUrl).into(imageView);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void OnSwitchClicked(View view){
        call = ((Switch) view).isChecked();
        Log.d("asd", "checked: "+call);
    }
    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(@NonNull View view){
        boolean checked = ((RadioButton) view).isChecked();

        if(view.getId() == R.id.osv10){
            Log.d("TIME", "10");
            if (checked){
                try{
                    t.cancel();
                    t = new Timer();
                    mTask = new ChronJob(this, webCrawler);
                    t.scheduleAtFixedRate(mTask, 0, 10000);
                }catch (Exception e){}
            }
        }
        else
        if(view.getId() == R.id.osv15){
            Log.d("TIME", "15");
            if (checked){
                try{
                    t.cancel();
                    t = new Timer();
                    mTask = new ChronJob(this, webCrawler);
                    t.scheduleAtFixedRate(mTask, 0, 15000);
                }catch (Exception e){}
            }
        }
        else
        if(view.getId() == R.id.osv20){
            Log.d("TIME", "20");
            if (checked){
                try{
                    t.cancel();
                    t = new Timer();
                    mTask = new ChronJob(this, webCrawler);
                    t.scheduleAtFixedRate(mTask, 0, 20000);
                }catch (Exception e){}
            }
        }
        else
        if(view.getId() == R.id.osv30){
            Log.d("TIME", "30");
            if (checked){
                try{
                    t.cancel();
                    t = new Timer();
                    mTask = new ChronJob(this, webCrawler);
                    t.scheduleAtFixedRate(mTask, 0, 30000);
                }catch (Exception e){}
            }
        }

    }
}

