package callAPI;

import android.content.Context;
import android.util.Log;

/**
 * No used
 * Super class of all the get api.
 * The CallAPIGET, unlike the post; doesn't define the url at creation. Rather it defines it in the doInBackGround.
 */
public class CallAPIGET extends CallAPI {

    String url;

    public CallAPIGET(Context context, String url){
        super(context);
        this.url = url;
    }

    protected String doInBackground(String... params) {
        if(params.length != 0){
            url += "?";
        }

        for(int i = 0; i< params.length; i=i+2){
            if(i != 0){url += "&";}
            Log.i("callApi", "boucle");
            url += params[i] + "=" + params[i+1];
        }
        Log.i("URLGet", url);
        setRequest(url, "GET");

        //As the params were inserted in the adress, we don't need to pass them
        // to the superior class again
        return super.doInBackground();
    }

    /*Functions to personalize the url for the subclasses */
    protected String getUrl(){
        return url;
    }
    protected void setURL(String url){
        this.url = url;
    }

}
