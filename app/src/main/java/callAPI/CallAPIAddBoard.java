package callAPI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.LoadingActivity;
import com.michel.adrien.projectpostit.R;

import org.json.JSONObject;

import exception.NotLoggedInException;
import settings.ApiUrl;

/*
    APIcall to create a new board for the user currently connected.
    Sended by the add board button from the side menu of the main activity.
 */
public class CallAPIAddBoard extends CallAPIPOST {

    public CallAPIAddBoard(Context context){
        super(context, ApiUrl.getCreateBoardsRoute());
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

    @Override
    public void onPostExecute(String result){
        Intent intent = null;
        //Si ca marche : faire un intent ver Loading activity.
        try {
            JSONObject json = new JSONObject(result);
            if(! json.isNull("error")){
                result = json.getJSONObject("error").getString("message");
            }
            else {
                result = getContext().getResources().getString(R.string.board_successfully_added);

                intent = new Intent(getContext(), LoadingActivity.class);
            }
        }
        catch (Throwable t) {
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();

        if(intent != null){
            Log.i("AddBoard", "nous lancons l intent");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }
}
