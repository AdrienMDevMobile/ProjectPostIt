package com.michel.adrien.projectpostit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import complementaryClass.confirmPasswordFragment;
import settings.stringLengthControl;


public class SignUpActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hide the keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*Sign up button */
        Button signUp = (Button)findViewById(R.id.buttonSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ((EditText) findViewById(R.id.editTextLogin)).getText().toString();
                Log.i("l", login);
                String password =
                        ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
                Log.i("p", password);
                String mail = ((EditText) findViewById(R.id.editTextMail)).getText().toString();
                Log.i("m", mail);
                /* After getting all the informations from the user we check their length.
                If they are good, we show the confirm password fragment */
                if (stringLengthControl.checkLoginLength(getBaseContext(), login) &&
                        stringLengthControl.checkMailLength(getBaseContext(), mail) &&
                        stringLengthControl.checkPasswordLength(getBaseContext(), password)) {
                    DialogFragment confirmPasswordDialog = new confirmPasswordFragment();
                    confirmPasswordDialog.show(getSupportFragmentManager(), "");
                }
            }
        });

        /*Return to login button */
        Button goToLogIn = (Button)findViewById(R.id.buttonGoToLogin);
        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
            }
        });
    }
}
