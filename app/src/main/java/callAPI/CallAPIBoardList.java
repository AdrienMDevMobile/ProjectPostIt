package callAPI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.MainActivity;
import com.michel.adrien.projectpostit.R;

import org.json.JSONException;
import org.json.JSONObject;

import exception.NotLoggedInException;
import settings.ApiUrl;

/*
    Load the board list for the side menu of the main activity.
    The callAPI is sended by Loading activity and it creates an intent toward Main activity
 */
public class CallAPIBoardList extends CallAPIPOST {

    public final static String INTENT_EXTRA_BOARDS = "extraBoards";

    public CallAPIBoardList(Context context) throws NotLoggedInException {
        super(context, ApiUrl.getBoardListRoute());
    }

    @Override
    protected String doInBackground(String... params) {
        //Add the Id of the user, and the token at the end of the params table
        try {
            return super.doInBackground(addUserIdandToken(params));
        }
        catch (NotLoggedInException n){
            this.cancel(true);
            return "";
        }

    }

    protected void onPostExecute(String result) {
        Log.i("boardlist", "We enter onPostExecute");
        Log.i("boardlist", result);

        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            JSONObject json = new JSONObject(result);

            intent.putExtra(INTENT_EXTRA_BOARDS, json.getJSONArray("boards").toString());

            getContext().startActivity(intent);
        }
        catch (JSONException e){
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

        getContext().startActivity(intent);

    }
}
