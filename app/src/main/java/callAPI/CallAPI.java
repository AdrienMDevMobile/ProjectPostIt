package callAPI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/*
Origin class of all the callAPI.
                    callAPI
         |             |                |
       callAPIGet   callAPIPOST      callAPIPUT
         |             |                |
     other get API  other post API   other post API

Then : even : name of the value, odd : value */

public abstract class CallAPI extends AsyncTask<String, String, String> {

    private Context context;
    private String adressUrl;
    private String requestMethode;
    private boolean doOutput = false;

    public CallAPI(Context context){
        this.context=context.getApplicationContext();
    }


    public Context getContext(){
        return context;
    }

    protected void setRequest(String adressUrl, String methode){
        this.adressUrl = adressUrl;
        this.requestMethode = methode;
    }

    protected void setRequest(String adressUrl, String methode, Boolean doOutput){
        setRequest(adressUrl, methode);

        this.doOutput = doOutput;
    }


    @Override
    protected String doInBackground(String... params) {

        String response ="";

        try {
            URL url = new URL(adressUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethode);
            conn.setDoOutput(doOutput);

            Log.i("callApi", "avant boucle");
            StringBuilder postData = new StringBuilder();
            for (int i = 0; i< params.length; i=i+2) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(params[i], "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(params[i+1], "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            Log.i("callApi", "on ecrit");
            conn.getOutputStream().write(postDataBytes);

            System.out.println("connection sended");

            // read the response
            System.out.println("Response Code: " + conn.getResponseCode());
            InputStream in = new BufferedInputStream(conn.getInputStream());
            /*
            response = in.toString();
            System.out.println(response);
            return response;*/

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
             response = sb.toString();
            Log.i("resposne", response);

            return response;
        }
        catch(MalformedURLException e){
            Log.i("malformedURL", adressUrl);
        }
        catch(IOException e){
            Log.i("IOException", e.getMessage()); //Sometime this can fail
        }
        catch (Exception e){
            Log.i("Exception", e.getMessage());
        }

        return response;

    }

    protected void onPostExecute(String result) {

    }

} // end CallAPI

/*


public abstract class CallAPI extends AsyncTask<String, String, String> {

    private Context context;
    private HttpRequestBase request;


    public CallAPI(Context context){
        this.context=context.getApplicationContext();
        this.request = request;
    }


Those functions are call by the inferior classes to define and get the HttpBase request.
request is a HttpGet for the APIGet subclass. A HttpPost for the APIPost subclass

    protected  HttpRequestBase getRequest(){
        return request;
    }
    protected void setRequest(HttpRequestBase request){
        this.request =request;
    }

    @Override
    protected String doInBackground(String... params) {

        HttpClient httpclient;
        HttpResponse response = null;
        String result = " ";

        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        //We make the list that contains all the infos for the Post request.
        //We also make the String that will added for the GET request url
        Log.i("callApi", "avant boucle");
        for(int i = 0; i< params.length; i=i+2){
            Log.i("callApi", "boucle");
            postParameters.add(new BasicNameValuePair(params[i], params[i + 1]));
        }

        Log.i("callAPI", "fin de la boucle");

        try {
            httpclient = new DefaultHttpClient();

            //If Post request, we add the parameters to the entity
            if(request.getClass() == HttpPost.class){
                ((HttpPost)request).setEntity(
                        new
                                UrlEncodedFormEntity(
                                postParameters
                        ));
            }

            response = httpclient.execute(request);
        }
        catch (Exception e) {
            result = "error";
        }

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;

            while ((line = rd.readLine()) != null)
            {
                result = result + line ;
            }
        } catch (Exception e) {
            result = "error";
        }
        Log.i("j", result);
        return result;

    }


    public Context getContext(){
        return context;
    }

    //Function that adds the url at the beggining of the params array
    //Return a new instance of the params table
    //If the params are empty, we
    protected String[] addUrl(String[] params, String url){


            String nouvParams[] = new String[params.length + 1];
            nouvParams[0] = url;

            for (int compter = 0; compter < params.length; compter++) {
                Log.i("addUrl", "a");
                nouvParams[compter + 1] = params[compter];
            }
            return nouvParams;

    }

} // end CallAPI */

