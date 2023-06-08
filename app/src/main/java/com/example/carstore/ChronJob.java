package com.example.carstore;

import android.os.AsyncTask;
import android.util.Log;

import java.util.TimerTask;

public class ChronJob extends TimerTask {
    MainActivity mainActivity;
    WebCrawler webCrawler;

    public ChronJob(MainActivity ma, WebCrawler wc){
        mainActivity = ma;
        webCrawler = wc;
    }

    @Override
    public void run() {
        Log.d("CHRON", "10 SECONDS PASSED!!!!!!!!!!!");
        String url = "https://www.willhaben.at/iad/gebrauchtwagen/auto/gebrauchtwagenboerse?sfId=fd2febc1-ec72-4158-bca0-0809c470d311&isNavigation=true&DEALER=1&page=1&rows=10&PRICE_TO=10000"; // Replace with the actual URL
        WebCrawler webCrawler = new WebCrawler(mainActivity);
        webCrawler.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }
}
