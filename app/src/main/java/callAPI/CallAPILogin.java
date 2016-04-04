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

import settings.ApiUrl;

/*
    CallAPI to log in the user.
    Sended by login activity.
    It then redirects to loading activites to get the board list.
 */
public class CallAPILogin extends CallAPIPOST {

    public CallAPILogin(Context context){
        super(context, ApiUrl.getUserLoginRoute());
    }

    protected void onPostExecute(String result) {
        try {
            JSONObject json = new JSONObject(result);

            if(! json.isNull(getContext().getString(R.string.json_answer_error)) || ! json.getBoolean(getContext().getString(R.string.json_response_servor))){
                //If fails (returns an error)
                result = json.getString("error");
            }
            else {
                String userId = json.getString("userId");
                Log.i("uId", userId);
                String sessionKey = json.getString("key");

                Log.i("LoginAPI", "we set into the shared preferences");
                Log.i("sessionKey", sessionKey);
                Log.i("userId", userId);

                SharedPreferences sharedPreferences = getContext().getSharedPreferences(getContext().getString(R.string.sharedPreferences_session), 0);
                Editor editor = sharedPreferences.edit();
                editor.putString(getContext().getString(R.string.sharedPreferences_values_session_token), sessionKey);
                editor.putString(getContext().getString(R.string.sharedPreferences_values_user_id), userId);
                editor.commit();

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
