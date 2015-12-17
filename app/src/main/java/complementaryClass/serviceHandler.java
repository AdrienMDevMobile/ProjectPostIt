package complementaryClass;

import android.util.Log;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by Adrien on 04/12/2015.
 */
public class serviceHandler {
    public final static int GET = 1;
    public final static int POST = 2;

    /*
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
    public static void makeServiceCall(String url, int method) {
        serviceHandler.makeServiceCall(url, method, null);
    }

    /*
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
    public static void makeServiceCall(String url, int method,
                                List<NameValuePair> params) {

        // appending params to url
        Log.e("in GET", "in GET");
        /*
        if (params != null) {
            Log.e("in GET params", "in GET params");

           String paramString = URLEncodedUtils
                    .format(params, "utf-8");
            url += "?" + paramString;
            Log.e("URL", url);
        } */

        new callAPI().execute(url);
    }

}