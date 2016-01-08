package settings;

import android.content.Context;

import complementaryClass.loggedInCheck;
import exceptions.notLoggedInException;

/**
 * Created by Adrien on 15/11/2015.
 */
public abstract class apiUrl {
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    private static String databaseUrl = "http://10.0.2.2:3000/api/";


    public static String getUserRoute(){
        return getDatabaseUrl() + "Members/";
    }
    public static String getUserRegisterRoute(){ return getUserRoute(); }
    public static String getUserLoginRoute(){ return getUserRoute() + "login"; }

    public static String getSelectedUseRoute(Context context, String userId) throws notLoggedInException {
        /*IF no user is given, we take the user that is currently connected */
        if(userId==null){
                userId = loggedInCheck.getLogInUserId(context);
        }
        return getUserRoute()  + userId + "/";
    }
    public static String getUserBoardsRoute(Context context, String userId)  throws notLoggedInException{
        return getSelectedUseRoute(context, userId) + "boards/";
    }

   // public static String getBoardRoute() { return getDatabaseUrl() + "Board/";}
}
