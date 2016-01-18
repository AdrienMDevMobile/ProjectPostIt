package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import callAPI.CallAPIBoardLista;
import complementaryClass.LoggedInCheck;
import exceptions.NotLoggedInException;

/*
Loading activities asks for the Boards lists and when it receives them, it opens MainActivity.
 */
public class LoadingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        /*
        TODO : make an intent to login Activity if the user is not logged in.
         */
        try {
            new CallAPIBoardLista(getBaseContext(), null).execute("access_token", LoggedInCheck.getLogInToken(getBaseContext()));
        }
        catch (NotLoggedInException e){
            Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
