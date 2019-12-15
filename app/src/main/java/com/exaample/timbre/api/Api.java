package com.exaample.timbre.api;

import android.os.AsyncTask;

import com.exaample.timbre.models.Timbru;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api extends AsyncTask<Void, Void, String> {

    final OkHttpClient client = new OkHttpClient();

    final Request request = new Request.Builder()
            //.url("http://www.mocky.io/v2/5dd8e2843100007400055ef2")
            .url("http://www.mocky.io/v2/5de00bbb350000225e480af4")
            .build();
    @Override
    protected String doInBackground(Void... voids) {

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                return null;
            }
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Timbru> parseResponse(String responseBody) {
        List<Timbru> timbre = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(responseBody);
            JSONObject jsonObject22 = root.getJSONObject("22.11.2019");
            JSONObject jsonObject23 = root.getJSONObject("23.10.2019");

            JSONArray favorite22 = jsonObject22.getJSONArray("Favorite");
            JSONArray favorite23 = jsonObject23.getJSONArray("Favorite");

            for (int i = 0; i < favorite22.length(); i++) {
                JSONObject jsonTimbru = favorite22.getJSONObject(i);
                Timbru timbru = new Timbru();
                timbru.setId(jsonTimbru.getInt("id"));
                timbru.setSerie(jsonTimbru.getString("serie"));
                timbru.setTematica(jsonTimbru.getString("tematica"));
                timbru.setAn(jsonTimbru.getInt("an"));
                timbru.setMarime((jsonTimbru.getString("marime")));

                timbre.add(timbru);
            }

            for (int i = 0; i < favorite23.length(); i++) {
                JSONObject jsonTimbru = favorite23.getJSONObject(i);
                Timbru timbru = new Timbru();
                timbru.setId(jsonTimbru.getInt("id"));
                timbru.setSerie(jsonTimbru.getString("serie"));
                timbru.setTematica(jsonTimbru.getString("tematica"));
                timbru.setAn(jsonTimbru.getInt("an"));
                timbru.setMarime(jsonTimbru.getString("marime"));

                timbre.add(timbru);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timbre;
    }

}


