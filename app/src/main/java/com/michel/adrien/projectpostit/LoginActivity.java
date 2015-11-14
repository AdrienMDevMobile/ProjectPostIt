package com.michel.adrien.projectpostit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import complementaryClass.InputStreamOperations;


public class LoginActivity extends ActionBarActivity {

    //API URL
    public final static String apiURL = "http://10.0.2.2:3000/user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        new CallAPI().execute("");

    }

    private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Log.i("Test", "0");
                String myurl= "http://10.0.2.2:3000/user";

                URL url = new URL(myurl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Log.i("Test", "1");

                /*
                 * InputStreamOperations est une classe complémentaire:
                 * Elle contient une méthode InputStreamToString.
                 */
                String result = InputStreamOperations.InputStreamToString(inputStream);

                // On récupère le JSON complet
                JSONObject jsonObject = new JSONObject(result);

                String s = jsonObject.getString("res");
                Log.i("Test", s);

                Log.i("Test", "3");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";

        }

        protected void onPostExecute(String result) {

        }

    } // end CallAPI
}
