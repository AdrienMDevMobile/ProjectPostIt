package callAPI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.michel.adrien.projectpostit.MainActivity;

import exception.NotLoggedInException;
import settings.ApiUrl;

/*
    Load the board list for the side menu of the main activity.
    The callAPI is sended by Loading activity and it creates an intent toward Main activity
 */
public class CallAPIBoardList extends CallAPIGET {

    public final static String INTENT_EXTRA_BOARDS = "extraBoards";

    public CallAPIBoardList(Context context, String userId, String idToken) throws NotLoggedInException {
        super(context, ApiUrl.getUserBoardsRoute(context, userId, idToken));
    }


    protected void onPostExecute(String result) {
        Log.i("boardlist", "We enter onPostExecute");
        Log.i("boardlist", result);

        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra(INTENT_EXTRA_BOARDS, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getContext().startActivity(intent);

    }
}
