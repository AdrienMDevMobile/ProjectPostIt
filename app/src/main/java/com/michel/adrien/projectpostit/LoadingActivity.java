package com.michel.adrien.projectpostit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import callAPI.CallAPIBoardList;
import complementaryClass.LoggedInCheck;
import exception.NotLoggedInException;

/*
Loading activities asks for the Boards lists with CallAPIBoardList and when it receives them,
it opens MainActivity.
 */
public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Log.i("Activity", "LoadingActivity");
        try {
            new CallAPIBoardList(getBaseContext(), null, LoggedInCheck.getLogInToken(getBaseContext())).execute();
        }
        catch (NotLoggedInException e){
            e.askForLogin(getBaseContext());
        }
    }

}
