package com.michel.adrien.projectpostit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import complementaryClass.ConfirmPasswordFragmenta;
import complementaryClass.LoggedInChecka;
import settings.StringControla;


public class SignUpActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Redirect if the user is loggedIn
        Intent intentIfLoggedIn = new Intent(SignUpActivity.this, LoadingActivity.class);
        if(LoggedInChecka.isLoggedIn(getBaseContext())){
            startActivity(intentIfLoggedIn);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hide the keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*Sign up button */
        Button signUp = (Button)findViewById(R.id.sign_up_activity_btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.sign_up_activity_etUsername)).getText().toString();
                String password =
                        ((EditText) findViewById(R.id.sign_up_activity_etPassword)).getText().toString();
                String email = ((EditText) findViewById(R.id.sign_up_activity_etEmail)).getText().toString();
                /* After getting all the informations from the user we check their length.
                If they are good, we show the confirm password fragment */
                if (StringControla.checkUsernameLength(getBaseContext(), username) &&
                        StringControla.checkEmailLength(getBaseContext(), email) &&
                        StringControla.checkPasswordLength(getBaseContext(), password) &&
                         StringControla.is_Valid_Email(getBaseContext(), email))
                {

                    DialogFragment confirmPasswordDialog = ConfirmPasswordFragmenta.newInstance(username, email, password);
                    confirmPasswordDialog.show(getSupportFragmentManager(), "");
                }
            }
        });

        /*Return to username button */
        Button goToLogin = (Button)findViewById(R.id.sign_up_activity_btnGoToLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
            }
        });
    }
}
