package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import complementaryClass.postItTextDesign;
import exception.NotLoggedInException;
import settings.ApiUrl;

/**
 * Get the list of post it for the active board.
 */
public class CallAPIGetPostItList extends CallAPIPOST {
    private LinearLayout currentLayout;

    public CallAPIGetPostItList(Context context, LinearLayout currentLayout){
        super(context, ApiUrl.getPostItListRoute());
        if(currentLayout == null){
            Log.w("PROBLEME", "PROBLEME");
        }
        this.currentLayout = currentLayout;
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
            } else {
                JSONArray postItArray = json.getJSONArray(getContext().getString(R.string.json_postIt_response));
                for (int i = 0; i < postItArray.length(); ++i) {
                    JSONObject j = postItArray.getJSONObject(i);
                    JSONObject jpostIt = j.getJSONObject(getContext().getString(R.string.json_postIt_object));
                    String type = jpostIt.getString(getContext().getString(R.string.json_postIt_type_name));

                    if(type.equals(getContext().getString(R.string.json_postIt_type_text))){ //If it is a text
                        currentLayout.addView(postItTextDesign.getPostItTextView(getContext(),
                                jpostIt.getString(getContext().getString(R.string.json_postIt_text_value))
                              //  ,json.getJSONObject("author").getString("username")
                                ,j.getJSONObject(getContext().getString(R.string.json_postIt_author_object)).getString(getContext().getString(R.string.json_postIt_author_name))
                               , jpostIt.getString(getContext().getString(R.string.json_postIt_time_value))

                                ));
                                //currentLayout.addView(new LinearLayout(getContext()));
                    }


                }
            }
        }
        catch(JSONException e){
            Toast.makeText(getContext(), getContext().getString(R.string.json_answer_error_message), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
    }
}
