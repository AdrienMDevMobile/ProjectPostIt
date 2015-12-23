package com.michel.adrien.projectpostit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
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
        Button signUp = (Button)findViewById(R.id.sign_up_activity_btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.sign_up_activity_etUsername)).getText().toString();
                String password =
                        ((EditText) findViewById(R.id.sign_up_activity_etPassword)).getText().toString();
                String mail = ((EditText) findViewById(R.id.sign_up_activity_etEmail)).getText().toString();
                /* After getting all the informations from the user we check their length.
                If they are good, we show the confirm password fragment */
                if (stringLengthControl.checkUsernameLength(getBaseContext(), username) &&
                        stringLengthControl.checkEmailLength(getBaseContext(), mail) &&
                        stringLengthControl.checkPasswordLength(getBaseContext(), password)) {

                    DialogFragment confirmPasswordDialog = confirmPasswordFragment.newInstance(username, mail, password);
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
