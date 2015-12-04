package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import complementaryClass.serviceHandler;
import settings.apiUrl;
import settings.stringLengthControl;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String login = ((EditText) findViewById(R.id.login_activity_etLogin)).getText().toString();

        //Button that sends a request to connect the user
        Button logIn = (Button)findViewById(R.id.login_activity_btnLogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ((EditText)findViewById(R.id.login_activity_etLogin)).getText().toString();
                String password = ((EditText)findViewById(R.id.login_activity_etPassword)).getText().toString();

                /* After getting all the informations from the user we check their length.
                If they are good, we show the confirm password fragment */
                if (stringLengthControl.checkLoginLength(getBaseContext(), login) &&
                        stringLengthControl.checkPasswordLength(getBaseContext(), password)) {

                    //Call of the API

                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("login", login));
                    params.add(new BasicNameValuePair("password", password));

                    serviceHandler.makeServiceCall(apiUrl.getUserRegisterRoute(), 1, params);
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
