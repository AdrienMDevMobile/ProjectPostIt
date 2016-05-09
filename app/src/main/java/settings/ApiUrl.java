package settings;

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

    public static String getPostItRoute() {  return getDatabaseUrl() + "PostIts/";  }
    public static String getAddPostItRoute() { return getPostItRoute() + "addPostIt/"; }
    public static String getPostItListRoute(){ return getPostItRoute() + "getPostItList/";}

}
