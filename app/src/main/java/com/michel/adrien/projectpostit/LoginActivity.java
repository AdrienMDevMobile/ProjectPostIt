package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import complementaryClass.InputStreamOperations;
import settings.apiUrl;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creation of the actions of the buttons
        //Button Sign up that starts the Sign up activity
        Button goToSignUp = (Button)findViewById(R.id.buttonGoToSignUp);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });

        Button logIn = (Button)findViewById(R.id.buttonLogin);
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String login = ((EditText)v.findViewById(R.id.editTextLogin)).getText().toString();
                String password = ((EditText)v.findViewById(R.id.editTextPassword))
                        .getText().toString();
            }
        });

        //new CallAPI().execute("");

    }

    private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String myUrl= apiUrl.getUserRoute();

                URL url = new URL(myUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                /*
                 * InputStreamOperations est une classe complémentaire:
                 * Elle contient une méthode InputStreamToString.
                 */
                String result = InputStreamOperations.InputStreamToString(inputStream);

                // On récupère le JSON complet
                JSONObject jsonObject = new JSONObject(result);

                String s = jsonObject.getString("res");


            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";

        }

        protected void onPostExecute(String result) {

        }

    } // end CallAPI
}
