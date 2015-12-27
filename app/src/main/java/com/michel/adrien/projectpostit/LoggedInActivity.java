package com.michel.adrien.projectpostit;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class LoggedInActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        SharedPreferences settings = getSharedPreferences( Resources.getSystem().getString(R.string.sharedPreferences_session), 0);
        String token = settings.getString(Resources.getSystem().getString(R.string.sharedPreferences_values_session_token), null);

        if(token != null){
            Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        }
    }

}
