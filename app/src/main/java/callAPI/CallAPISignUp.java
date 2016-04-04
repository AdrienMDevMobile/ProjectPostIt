package callAPI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.LoginActivity;
import com.michel.adrien.projectpostit.R;

import org.json.JSONObject;

import settings.ApiUrl;

/*
    callAPI to sign up the user.
    It is called by Sign up activity
    It redirects to login activity is the registration is successfull.
 */
public class CallAPISignUp extends CallAPIPOST {

    public CallAPISignUp(Context context){
        super(context, ApiUrl.getUserRegisterRoute());
    }

    protected void onPostExecute(String result) {

        Intent intent = null;

        try {
            JSONObject json = new JSONObject(result);
            if(! json.isNull(getContext().getString(R.string.json_answer_error)) || ! json.getBoolean(getContext().getString(R.string.json_response_servor))){
                result = json.getJSONObject(getContext().getString(R.string.json_answer_error))
                        .getString(getContext().getString(R.string.json_answer_error_message));
            }
            else {
                String successful = getContext().getResources().getString(R.string.signUp_successful);
                String username = getContext().getResources().getString(R.string.username) + " " + json.getString("username");
                String email = getContext().getResources().getString(R.string.email) + " " + json.getString("email");

                result = successful + " " + username + " " + email;

                intent = new Intent(getContext(), LoginActivity.class);
            }
        }
        catch (Throwable t) {
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

        CharSequence text = result;
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();

        if(intent != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }
}
