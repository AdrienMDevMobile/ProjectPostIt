package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

import org.json.JSONObject;

import settings.ApiUrl;

public class callAPISignUp extends callAPIPOST {

    public callAPISignUp(Context context){
        super(context, ApiUrl.getUserRegisterRoute());
    }

    @Override
    protected String doInBackground(String... params) {
        Log.i("api", "We enter doInBackGround for call API Sign up");
        //params[0] =

        Log.i("api", "We are about to start the super.doInBackground");
        return super.doInBackground(params);
    }

    protected void onPostExecute(String result) {

        try {
            JSONObject json = new JSONObject(result);
            if(! json.isNull("error")){
                result = json.getJSONObject("error").getString("message");
            }
            else {
                String successful = getContext().getResources().getString(R.string.signUp_successful);
                String username = getContext().getResources().getString(R.string.username) + " " + json.getString("username");
                String email = getContext().getResources().getString(R.string.email) + " " + json.getString("username");

                result = successful + " " + username + " " + email;
            }
        }
        catch (Throwable t) {
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

        CharSequence text = result;

        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }
}
