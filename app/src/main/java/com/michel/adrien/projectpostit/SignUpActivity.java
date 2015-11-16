package com.michel.adrien.projectpostit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v4.app.DialogFragment;

import complementaryClass.confirmPasswordFragment;


public class SignUpActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hide the keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button signUp = (Button)findViewById(R.id.buttonSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment confirmPasswordDialog = new confirmPasswordFragment();
                confirmPasswordDialog.show(getSupportFragmentManager(), "aaa");
            }
        });

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
