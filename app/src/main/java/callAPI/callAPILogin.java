package callAPI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.LoadingActivity;
import com.michel.adrien.projectpostit.R;

import org.json.JSONException;
import org.json.JSONObject;

import settings.ApiUrla;

/**
 * Created by Adrien on 27/12/2015.
 */
public class callAPILogin extends callAPIPOST {

    public callAPILogin(Context context){
        super(context, ApiUrla.getUserLoginRoute());
    }

    @Override
    protected String doInBackground(String... params) {

        return super.doInBackground(params);
    }

    protected void onPostExecute(String result) {
        try {
            JSONObject json = new JSONObject(result);

            if(! json.isNull("error")){
                //If fails (returns an error)
                result = json.getJSONObject("error").getString("message");
            }
            else {
                String sessionKey = json.getString("id");
                String userId = json.getString("userId");
                Log.i("LoginAPI", "we set into the shared preferences");
                Log.i("sessionKey", sessionKey);
                Log.i("userId", userId);

                SharedPreferences sharedPreferences = getContext().getSharedPreferences(getContext().getString(R.string.sharedPreferences_session), 0);
                Editor editor = sharedPreferences.edit();
                editor.putString(getContext().getString(R.string.sharedPreferences_values_session_token), sessionKey);
                editor.putString(getContext().getString(R.string.sharedPreferences_values_user_id), userId);
                editor.commit();

               /* Context t = getContext().getApplicationContext();
                getContext().startActivity(new Intent(getContext(), LoadingActivity.class)); */
                Intent intent = new Intent(getContext(), LoadingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getContext().startActivity(intent);
            }
        }
        catch (JSONException t) {
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

        CharSequence text = result;

        Log.i("l", result);
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }


}
