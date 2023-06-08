package com.example.carstore;

import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler extends AsyncTask<String, Void, JSONArray> {
    MainActivity mainActivity;
    public WebCrawler(MainActivity ma){
        mainActivity = ma;
    }
    @Override
    protected JSONArray doInBackground(String... urls) {
        String url = urls[0];
        try {
            URL endpoint = new URL(url);
            Log.d("endpoint","response is: "+endpoint);
            HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
            connection.setRequestMethod("GET");

            Log.d("connection","response is: "+connection);
            int responseCode = connection.getResponseCode();
            Log.d("getResponseCode","response is: "+responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                String jsonString = response.toString();
                /*int startIndex = jsonString.indexOf("<script id=\"__NEXT_DATA__\" type=\"application/json\">") + "<script id=\"__NEXT_DATA__\" type=\"application/json\">".length();
                int endIndex = jsonString.indexOf("</script>");
                String jsonSubstring = jsonString.substring(endIndex, startIndex);
                JSONObject result = new JSONObject(jsonSubstring);*/
                String temp = response.substring(response.indexOf("<script id=\"__NEXT_DATA__\" type=\"application/json\">") + "<script id=\"__NEXT_DATA__\" type=\"application/json\">".length());
                String substringEnd = "</script>";
                int endIndex = temp.indexOf(substringEnd);
                String jsonSubstring = temp.substring(0, endIndex);
                JSONObject result = new JSONObject(jsonSubstring);

                JSONObject j1 = result.getJSONObject("props");
                JSONObject j2 = j1.getJSONObject("pageProps");
                JSONObject j3 = j2.getJSONObject("searchResult");
                JSONObject j4 = j3.getJSONObject("advertSummaryList");
                JSONArray advertSummaryList =  j4.getJSONArray("advertSummary");

                JSONArray returnArray = new JSONArray();



                for (int i = 0; i < advertSummaryList.length(); i++) {
                    JSONObject returnObj = advertSummaryList.getJSONObject(i);
                    JSONArray attributeList = returnObj.getJSONObject("attributes").getJSONArray("attribute");

                    for (int j = 0; j < attributeList.length(); j++) {
                        JSONObject element = attributeList.getJSONObject(j);
                        String name = element.getString("name").toLowerCase();
                        String value = element.getJSONArray("values").getString(0);
                        returnObj.put(name, isNumeric(value) ? Integer.parseInt(value) : value);
                    }

                    // Delete useless keys
                    returnObj.remove("attributes");
                    returnObj.remove("contextLinkList");
                    returnObj.remove("advertiserInfo");
                    returnObj.remove("advertImageList");

                    returnArray.put(returnObj);
                }

                return returnArray;
            } else {
                Log.e("HTTP Request", "HTTP request failed with response code: " + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        try{
            String heading = "";
            String price = "";

            if (result != null) {
                List<Item> items = new ArrayList<Item>();

                for (int i = 0; i < result.length(); i++) {
                    JSONObject item = result.getJSONObject(i);

                    heading = item.getString("heading");
                    price = item.getString("price/amount");

                    items.add(new Item(heading,price,mainActivity));
                }

                mainActivity.initiate(items);
            } else {
                // Handle error case
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}