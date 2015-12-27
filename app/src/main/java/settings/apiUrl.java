package settings;

/**
 * Created by Adrien on 15/11/2015.
 */
public abstract class apiUrl {
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    private static String databaseUrl = "http://10.0.2.2:3000/api/";


    public static String getUserRoute(){
        return getDatabaseUrl() + "users/";
    }
    public static String getUserRegisterRoute(){ return getUserRoute(); }
    public static String getUserLoginRoute(){ return getUserRoute() + "login"; }

    public static String getBoardRoute() { return getDatabaseUrl() + "board/";}
}
