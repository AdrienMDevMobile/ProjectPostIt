package complementaryClass;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Adrien on 27/12/2015.
 */
public class callAPILogin extends callAPISignUp {

    public callAPILogin(Context context){
        super(context);
    }

    protected void onPostExecute(String result) {
        CharSequence text = result;

        Log.i("l", result);
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }


}
