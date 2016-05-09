package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

import org.json.JSONException;
import org.json.JSONObject;

import exception.NotLoggedInException;
import settings.ApiUrl;

/**
 * Create a text post it on the boardd7
 */
public class callAPIAddPostIt extends CallAPIPOST{
    public callAPIAddPostIt(Context context, String postItType){
        super(context, ApiUrl.getAddPostItRoute());
    }

    @Override
    protected String doInBackground(String... params) {
        //Add the Id of the user, and the token at the end of the params table
        try {
            return super.doInBackground(addUserIdandToken(params));
        } catch (NotLoggedInException n) {
            this.cancel(true);
            return "";
        }
    }

    @Override
    public void onPostExecute(String result){
        try {
            JSONObject json = new JSONObject(result);
            if (!json.isNull("error")) {
                result = json.getJSONObject("error").getString("message");
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
            else {
                /*TODO
                Recharger la page pour afficher le nouveau post it
                 */
            }
        }
        catch(JSONException e){
            Toast.makeText(getContext(), getContext().getString(R.string.post_it_successfully_added), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
    }

}