package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

import org.json.JSONObject;

import exception.NotLoggedInException;
import settings.ApiUrl;

/*
    Class used by main activity when the user adds another user to the board.
 */
public class CallAPIAddUserToBoard extends CallAPIPOST{

    public CallAPIAddUserToBoard(Context context){
        super(context, ApiUrl.getAddUserToBoardRoute());
    }

    @Override
    protected String doInBackground(String... params) {
        //Add the Id of the user, and the token at the end of the params table
        try {
            addToken(params);
            return super.doInBackground();
        } catch (NotLoggedInException n) {
            this.cancel(true);
            return "";
        }
    }

    @Override
    public void onPostExecute(String result){
        try {
            JSONObject json = new JSONObject(result);
            if(! json.isNull("error")){
                result = json.getJSONObject("error").getString("message");

                //Error that is thrown by the server if the user doesn't exist
                if(result.equals("noUser")){
                    Toast.makeText(getContext(), R.string.notif_add_user_to_board_fail_text, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                }

            }
            else {
                if(json.getBoolean("response")){ //The user has been added to the board
                    result = getContext().getResources().getString(R.string.notif_add_user_to_board_success_text);
                    Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                }
                else { //The user is already on the board
                    Toast.makeText(getContext(), R.string.notif_user_already_on_board, Toast.LENGTH_LONG).show();
                }

            }
        }
        catch (Throwable t) {
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
    }
}