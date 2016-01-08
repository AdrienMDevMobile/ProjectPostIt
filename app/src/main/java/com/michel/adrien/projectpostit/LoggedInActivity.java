package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import callAPI.callAPIBoardListTest;
import complementaryClass.loggedInCheck;
import exceptions.notLoggedInException;

public class LoggedInActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        SharedPreferences settings = getSharedPreferences( getString(R.string.sharedPreferences_session), 0);
        String token = settings.getString(getString(R.string.sharedPreferences_values_session_token), null);

        if(token != null){
            Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        }

        Button logout = (Button)findViewById(R.id.logged_in_activity_btnLogOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences_session), 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(getBaseContext().getString(R.string.sharedPreferences_values_session_token));
                editor.remove(getBaseContext().getString(R.string.sharedPreferences_values_user_id));
                editor.commit();
                //editor.clear();
                Log.i("SharedPreferences", "Cleared");

                Intent intent = new Intent(LoggedInActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        try {
            new callAPIBoardListTest(getBaseContext(), (TextView) findViewById(R.id.logged_in_activity_twListBoards), null).execute("access_token", loggedInCheck.getLogInToken(getBaseContext()));
        }
        catch (notLoggedInException e){
            Toast.makeText(getBaseContext(), getString(R.string.error_not_logged_In), Toast.LENGTH_SHORT).show();
        }
        //Rechercher les differents boards
        //new callAPIGetBoards(getBaseContext()).execute(apiUrl.getUserLoginRoute(), "token", token);
    }

}
