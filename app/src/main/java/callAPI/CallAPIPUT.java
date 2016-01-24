package callAPI;

import android.content.Context;
import android.util.Log;

import org.apache.http.client.methods.HttpPut;

/**
 * Created by Adrien on 23/01/2016.
 */
public class CallAPIPUT extends CallAPI {
    public CallAPIPUT(Context context, String url){
        super(context);
        Log.i("URLPut", url);
        setRequest(new HttpPut(url));
    }
}
