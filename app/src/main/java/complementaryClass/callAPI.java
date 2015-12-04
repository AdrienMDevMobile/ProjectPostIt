package complementaryClass;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adrien on 04/12/2015.
 */
public class callAPI extends AsyncTask<String, String, String> {



    @Override
    /**
     * Param 0 : URL
     */
    protected String doInBackground(String... params) {

        try {
            Log.i("URL2", params[0]);
            URL url = new URL(params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);

            // On récupère le JSON complet
            JSONObject jsonObject = new JSONObject(result);

            String resultString = jsonObject.getString("res");

            Log.i("api", resultString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    protected void onPostExecute(String result) {

    }

} // end CallAPI

