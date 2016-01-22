package exceptions;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.michel.adrien.projectpostit.LoginActivity;
import com.michel.adrien.projectpostit.R;

/*
    Exception sended if a function requests the user to be connected while he is not.
 */
public class NotLoggedInException extends Exception{

    //This funciton ask the user to reconnect and redirects him to the login class.
    public void askForLogin(Context context){
        Toast.makeText(context, context.getString(R.string.please_login), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
