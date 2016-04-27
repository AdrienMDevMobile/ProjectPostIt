package settings;

import android.content.Context;

import complementaryClass.LoggedInCheck;
import exception.NotLoggedInException;

/**
 * Class that is used to get the differents adress of all the api.
 */
public abstract class ApiUrl {
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    private static String databaseUrl = "http://10.0.2.2:3000/";


    public static String getUserRoute(){
        return getDatabaseUrl() + "Members/";
    }
    public static String getUserRegisterRoute(){ return getUserRoute() + "register/"; }
    public static String getUserLoginRoute(){ return getUserRoute() + "login/"; }

    public static String getBoardsRoute() {  return getDatabaseUrl() + "Boards/";  }
    public static String getCreateBoardsRoute() {  return getBoardsRoute() + "addBoard/"; }
    public static String getBoardListRoute(){ return getBoardsRoute() + "getList/"; }
    public static String getAddUserToBoardRoute() { return getBoardsRoute() + "addUser/";}


    public static String getSelectedUseRoute(Context context, String userId) throws NotLoggedInException {
        /*IF no user is given, we take the user that is currently connected */
        if(userId==null){
                userId = LoggedInCheck.getLogInUserId(context);
        }
        return getUserRoute()  + userId + "/";
    }

    public static String getUnknownUserInformation(String username){
        return getUserRoute()+ "findOne?filter[where][username]=" + username;
    }

    public static String addUserToExistingBoardRoute(Context context, String userId, String boardId) throws  NotLoggedInException{
       // /Members/{id}/boards/rel/{fk}
        return getSelectedUseRoute(context, userId) + "boards/rel/" + boardId;
    }

   // public static String getBoardRoute() { return getDatabaseUrl() + "Board/";}
}
