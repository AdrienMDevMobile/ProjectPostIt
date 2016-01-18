package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import exceptions.NotLoggedInExceptiona;
import settings.ApiUrla;

/**
 * Created by Adrien on 30/12/2015.
 */
public class callAPIBoardListTest extends callAPIGET {

    TextView textView;

    public callAPIBoardListTest(Context context, TextView textView, String userId) throws NotLoggedInExceptiona {
        super(context, ApiUrla.getUserBoardsRoute(context, userId));
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
