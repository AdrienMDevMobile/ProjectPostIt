package com.michel.adrien.projectpostit;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import callAPI.callAPIBoardList;
import complementaryClass.loggedInCheck;
import exceptions.notLoggedInException;

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
            new callAPIBoardList(getBaseContext(), null).execute("access_token", loggedInCheck.getLogInToken(getBaseContext()));
        }
        catch (notLoggedInException e){
            Toast.makeText(getBaseContext(), getString(R.string.error_not_logged_In), Toast.LENGTH_SHORT).show();
        }
    }

}
