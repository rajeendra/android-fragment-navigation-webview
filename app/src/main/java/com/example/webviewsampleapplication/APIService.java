package com.example.webviewsampleapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIService {
    Context mContext;

    APIService(Context c) {
        mContext = c;
    }

    public String getUsers(String payLoad) {
        //Find url
        String mongodb_URL = "https://data.mongodb-api.com/app/data-deltp/endpoint/data/v1/action/find";

        String mongodb_API_KEY = "<API-KEY>";
        String mongodb_DATASOURCE = "Cluster0";
        String mongodb_DATABASE = "userDatabase";
        String mongodb_COLLECTION = "userDatabaseCollection";

        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = sharedPref.getString(ApplicationConstants.TOKEN, null);
        if ((token != null && !token.isEmpty()) ){
            mongodb_API_KEY = token;
        }

        String sResponse = null;

        try {
            URL url = new URL(mongodb_URL);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("api-key", mongodb_API_KEY);

            String data = "{ \"dataSource\": \"" + mongodb_DATASOURCE
                    + "\", \"database\": \"" + mongodb_DATABASE
                    + "\", \"collection\": \"" + mongodb_COLLECTION + "\" }";
            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream outStream = http.getOutputStream();
            outStream.write(out); // Server call
            outStream.close();

            // Read response
            InputStreamReader inputStreamReader = new InputStreamReader(http.getInputStream());

            // Read response
            BufferedReader br = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            sResponse = response.toString();
            http.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sResponse;
    }
}
