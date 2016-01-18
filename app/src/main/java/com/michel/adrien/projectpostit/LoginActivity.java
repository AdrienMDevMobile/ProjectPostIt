package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import callAPI.CallAPILogin;
import complementaryClass.LoggedInCheck;
import settings.StringControl;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Redirect if the user is loggedIn
        Intent intentIfLoggedIn = new Intent(LoginActivity.this, LoadingActivity.class);
        if(LoggedInCheck.isLoggedIn(getBaseContext())){
            startActivity(intentIfLoggedIn);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //PreferenceManager.setDefaultValues(this, R.xml.preference, false);


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
                if (StringControl.checkUsernameLength(getBaseContext(), username) &&
                        StringControl.checkPasswordLength(getBaseContext(), password)) {

                    //Call of the API
                    new CallAPILogin(getBaseContext()).execute("username", username, "password", password);
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

    }


}
