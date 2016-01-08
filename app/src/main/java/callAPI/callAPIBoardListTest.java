package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import exceptions.notLoggedInException;
import settings.apiUrl;

/**
 * Created by Adrien on 30/12/2015.
 */
public class callAPIBoardListTest extends callAPIGET {

    TextView textView;

    public callAPIBoardListTest(Context context, TextView textView, String userId) throws notLoggedInException{
        super(context, apiUrl.getUserBoardsRoute(context, userId));
        this.textView = textView;
    }

    protected void onPostExecute(String result) {
        Log.i("boardlistTest", "We enter onPostExecute");
        Log.i("boardlistTest", result);
        if(result != null){
            textView.setText(result);
        }
    }
}
