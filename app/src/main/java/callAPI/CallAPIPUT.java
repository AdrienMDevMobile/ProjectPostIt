package callAPI;

import android.content.Context;
import android.util.Log;

/*
    Super class of all the put apicalls
 */
public class CallAPIPUT extends CallAPI {
    public CallAPIPUT(Context context, String url){
        super(context);
        Log.i("URLPut", url);
        setRequest(url, "PUT");
    }
}
