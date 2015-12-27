package complementaryClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.michel.adrien.projectpostit.R;

import exceptions.notLoggedInException;

/**
 * Created by Adrien on 27/12/2015.
 */
public abstract class loggedInCheck {

    /*
    Private function that gives the value of the token. Is used for other functions of the class.
     */
    static private String getLoginTokenValue(Context context){
        Log.i("t", "Nous rentrons dans getLoginTokenValue");
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.sharedPreferences_session), 0);
        String token = settings.getString(context.getString(R.string.sharedPreferences_values_session_token), null);

        Log.i("t", "Nous sortons de getLoginTokenValue");
        return token;
    }

    /*
    Return boolean which says if the user is logged In or not
     */
    static public boolean isLoggedIn(Context context){
        Log.i("t", "Nous rentrons dans is loggedIn");
        String token = getLoginTokenValue(context);


        if(token != null){
            Log.i("t", "Nous sortons de isLoggedIn avec true");
            return true;
        }
        Log.i("t", "Nous sortons de isLoggedIn avec false");
        return  false;
    }

    /*
    Return the value of the session token. Throws an exception if the user is not logged in.
     */
    static public String getLogInToken(Context context) throws notLoggedInException {
        Log.i("t", "Nous rentrons dans is getLogInToken");
        if (!loggedInCheck.isLoggedIn(context)) {
            throw new notLoggedInException();
        }
        Log.i("t", "Nous sortons de isLoggedIn");
        return getLoginTokenValue(context);
    }




}
