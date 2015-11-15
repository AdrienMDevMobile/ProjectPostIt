package complementaryClass;

/**
 * Created by Adrien on 15/11/2015.
 */
public abstract class apiUrl {
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    private static String databaseUrl = "http://10.0.2.2:3000/";

    public static String getUserRoute(){
        return getDatabaseUrl() + "user/";
    }
}
