package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import complementaryClass.callAPILogin;
import complementaryClass.loggedInCheck;
import settings.apiUrl;
import settings.stringLengthControl;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Redirect if the user is loggedIn


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //PreferenceManager.setDefaultValues(this, R.xml.preference, false);

        Intent intentIfLoggedIn = new Intent(LoginActivity.this, SignUpActivity.class);

        SharedPreferences settings = getSharedPreferences(getString(R.string.sharedPreferences_session), 0);
        Log.i("t", "nous avonsu pris les share preferences");
        if(loggedInCheck.isLoggedIn(getBaseContext())){
            startActivity(intentIfLoggedIn);
        }

        String username = ((EditText) findViewById(R.id.login_activity_etUsername)).getText().toString();

        //Button that sends a request to connect the user
        Button login = (Button)findViewById(R.id.login_activity_btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText)findViewById(R.id.login_activity_etUsername)).getText().toString();
                String password = ((EditText)findViewById(R.id.login_activity_etPassword)).getText().toString();

                /* After getting all the informations from the user we check their length.
                If they are good, we show the confirm password fragment */
                if (stringLengthControl.checkUsernameLength(getBaseContext(), username) &&
                        stringLengthControl.checkPasswordLength(getBaseContext(), password)) {

                    //Call of the API
                    new callAPILogin(getBaseContext()).execute(apiUrl.getUserLoginRoute(), "username", username, "password", password);
                }
            }
        });

        //Creation of the actions of the buttons
        //Button Sign up that starts the Sign up activity
        Button goToSignUp = (Button)findViewById(R.id.login_activity_btnGoToSignUp);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });

        //new CallAPI().execute("");

    }


}
