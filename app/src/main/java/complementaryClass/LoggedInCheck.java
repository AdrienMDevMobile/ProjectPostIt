package complementaryClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.michel.adrien.projectpostit.R;

import exceptions.NotLoggedInException;

/**
 * Created by Adrien on 27/12/2015.
 */
public abstract class LoggedInCheck {

    /*
    Private function that gives the value of the value in the SharedReferences. Is used for other functions of the class.
     */
    static private String getSharedReferencesValue(Context context, String sharedReferencesName){
        Log.i("t", "Nous rentrons dans getLoginTokenValue");
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.sharedPreferences_session), 0);
        String toReturn = settings.getString(sharedReferencesName, null);

        Log.i("t", "Nous sortons de getLoginTokenValue");
        return toReturn;
    }

    /*
    Return boolean which says if the user is logged In or not
     */
    static public boolean isLoggedIn(Context context){
        Log.i("t", "Nous rentrons dans is loggedIn");
        String token = getSharedReferencesValue(context, context.getString(R.string.sharedPreferences_values_session_token));



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
    static public String getLogInToken(Context context) throws NotLoggedInException {
        Log.i("t", "Nous rentrons dans is getLogInToken");
        if (!LoggedInCheck.isLoggedIn(context)) {
            throw new NotLoggedInException();
        }
        Log.i("t", "Nous sortons de isLoggedIn");
        return getSharedReferencesValue(context, context.getString(R.string.sharedPreferences_values_session_token));
    }

    static public String getLogInUserId(Context context) throws NotLoggedInException {
        Log.i("t", "Nous rentrons dans is getLogInUserId");
        if (!LoggedInCheck.isLoggedIn(context)) {
            throw new NotLoggedInException();
        }
        Log.i("t", "Nous sortons de getLogInUserId");
        return getSharedReferencesValue(context, context.getString(R.string.sharedPreferences_values_user_id));
    }




}
