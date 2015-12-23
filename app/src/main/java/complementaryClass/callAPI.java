package complementaryClass;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class callAPI extends AsyncTask<String, String, String> {

    private Context context;

    public callAPI(Context context){
        this.context=context;
    }


    public Context getContext(){
        return context;
    }


    @Override
    protected String doInBackground(String... params) {

        HttpClient httpclient;
        HttpPost request;
        HttpResponse response = null;
        String result = " ";

        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        //On crée la liste qui contiendra tous nos paramètres
        //Et on y rajoute nos paramétres
        Log.i("e", "avant boucle");
        for(int i = 1; i< params.length; i=i+2){
            Log.i("e", "boucle");
            postParameters.add(new BasicNameValuePair(params[i],params[i+1]));
        }

        Log.i("try", "nous y sommes");

        try {
            httpclient = new DefaultHttpClient();
            Log.i("URL2", params[0]);
            request = new HttpPost(params[0]);
            Log.i("a", "1");
            request.setEntity(
                    new
                            UrlEncodedFormEntity(
                            postParameters
                    ));
            response = httpclient.execute(request);
            Log.i("a", "2");
        }
        catch (Exception e) {
            result = "error";
        }

        try {
            Log.i("a", "3");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            Log.i("a", "4");
            while ((line = rd.readLine()) != null)
            {
                result = result + line ;
            }
        } catch (Exception e) {
            result = "error";
        }
        Log.i("a", "fin");
        Log.i("j", result);
        return result;

    }

} // end CallAPI

