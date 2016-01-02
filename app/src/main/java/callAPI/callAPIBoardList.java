package callAPI;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

import complementaryClass.loggedInCheck;
import exceptions.notLoggedInException;
import settings.apiUrl;

/**
 * Created by Adrien on 30/12/2015.
 */
public class callAPIBoardList extends callAPIGET {

    TextView textView;

    public callAPIBoardList(Context context, TextView textView){
        super(context);
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String url =  apiUrl.getUserRoute();

        try {
            //http://localhost:3000/api/Members/5683d6ce1fd9b68c2f044b74/boards?access_token=LX8v4sECp65HvDI0otmZRzZGqRPvN84M93hLzchXBTZOd0DoC9nhotcaii6WShMm
            //Log.i("callBoardListuserId", loggedInCheck.getLogInUserId(getContext()));
            url += loggedInCheck.getLogInUserId(getContext()) + "/boards?access_token=" + loggedInCheck.getLogInToken(getContext());
        }
        catch(notLoggedInException e){
            Toast.makeText(getContext(), getContext().getString(R.string.error_not_logged_In), Toast.LENGTH_SHORT).show();
            return null;
        }

        return super.doInBackground(this.addUrl(params, url));
    }

    protected void onPostExecute(String result) {
        Log.i("boardlist", "We enter onPostExecute");
        Log.i("boardlist", result);
        if(result != null){
            textView.setText(result);
        }
    }
}
