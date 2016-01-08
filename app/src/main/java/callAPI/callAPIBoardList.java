package callAPI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.michel.adrien.projectpostit.MainActivity;

import exceptions.notLoggedInException;
import settings.apiUrl;

/**
 * Created by Adrien on 30/12/2015.
 */
public class callAPIBoardList extends callAPIGET {

    public final static String INTENT_EXTRA_BOARDS = "extraBoards";

    public callAPIBoardList(Context context, String userId) throws notLoggedInException{
        super(context, apiUrl.getUserBoardsRoute(context, userId));
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
