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
public class CallAPIAddUserToBoarda extends CallAPIGET{

    String activeBoardId;

    /*calls http://localhost:3000/api/Members/findOne?filter[where][username]=aaaaa
    to get the ID of the user
    Then calls  /Boards/{id}/users/rel/{fk}  to add the member to the board.
     */
    public CallAPIAddUserToBoarda(Context context, String activeBoardId, String username){
        super(context, ApiUrl.getUnknownUserInformation(username));
        this.activeBoardId = activeBoardId;

        Toast.makeText(context, activeBoardId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostExecute(String result){

        try {
            JSONObject json = new JSONObject(result);
            if(! json.isNull(getContext().getString(R.string.json_answer_error))){
                result = json.getJSONObject(getContext().getString(R.string.json_answer_error))
                        .getString(getContext().getString(R.string.json_answer_error_message));
            }
            else {
                String userId= json.getString(getContext().getString(R.string.json_user_id));
                Log.i("UnknownUserId", userId);

                new CallAPIFinishAddUserToBoard(getContext(), userId, activeBoardId).execute();
            }
        }
        catch(NotLoggedInException e){
            Toast.makeText(getContext(), getContext().getString(R.string.error_not_logged_In), Toast.LENGTH_LONG);
            Log.e("FinishAddUserToBoard", "errorNotLoggedIn");

        }
        catch (Throwable t) {
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

    }

    private class CallAPIFinishAddUserToBoard extends CallAPIPUT {

        public CallAPIFinishAddUserToBoard(Context context, String userId, String activeBoardId) throws NotLoggedInException{
            super(context, ApiUrl.addUserToExistingBoardRoute(context, userId, activeBoardId));

            Log.i("finishUserBoard", "done");
        }

        public void onPostExecute(String result){

            try {
                JSONObject json = new JSONObject(result);
                if(! json.isNull(getContext().getString(R.string.json_answer_error))){
                    result = json.getJSONObject(getContext().getString(R.string.json_answer_error))
                            .getString(getContext().getString(R.string.json_answer_error_message));
                }
                else {
                    Log.i("FinishAddUserToBoard", "successful");
                    Log.i("json", result);
                }
            }
            catch (Throwable t) {
                Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
                Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
            }
        }
    }
}