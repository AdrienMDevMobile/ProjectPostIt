package callAPI;

import android.content.Context;
import android.util.Log;

import complementaryClass.LoggedInCheck;
import exception.NotLoggedInException;

/*
 * Not used
    Super class of all the post apicalls
 */
public abstract class CallAPIPOST extends CallAPI {

    public CallAPIPOST(Context context, String url){
        super(context);
        Log.i("URLPost", url);
        setRequest(url, "POST", true);
    }

    //Function used by the subclasses. You choose if you add both or just one of them.
    protected String[] addUserIdandToken(boolean addId, boolean addToken, String... params) throws NotLoggedInException {

        int params2length = params.length;
        //For each params that we add, we grow the length of the params table by 2
        // (one for the param name, one for the param itself)
        if(addId){
            params2length += 2;
        }
        if(addToken){
            params2length += 2;
        }
        String[] params2 = new String[params2length];

        //Since params in doInBackground must be a table and not a list
        // , we cannot push additionnal parameters.
        //So we have to create a second table with more room for the additionnal arguments.
        int i = 0;
        for (String s :
                params) {
            params2[i] = s;
            i++;
        }
        //At the end of the loop, i = params.size
        if(addId){
            String userId = LoggedInCheck.getLogInUserId(getContext());
            params2[i] = "userId";
            params2[i + 1] = userId;
        }

        if(addToken) {
            String token = LoggedInCheck.getLogInToken(getContext());
            params2[i + 2] = "token";
            params2[i + 3] = token;
        }

        return params2;
    }

    protected String[] addUserIdandToken(String... params) throws NotLoggedInException{
        return (this.addUserIdandToken(true, true, params));
    }

    protected String[] addToken(String... params) throws NotLoggedInException{
        return (this.addUserIdandToken(false, true, params));
    }

}
