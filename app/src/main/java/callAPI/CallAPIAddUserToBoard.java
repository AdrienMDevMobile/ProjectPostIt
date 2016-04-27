package callAPI;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

import org.json.JSONException;
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
            return super.doInBackground(addToken(params));
        } catch (NotLoggedInException n) {
            this.cancel(true);
            return "";
        }
    }

    @Override
    public void onPostExecute(String result){
        Notification.Builder notificationBuilder = new Notification.Builder(getContext());

        try {
            JSONObject json = new JSONObject(result);
            if(! json.isNull("error")){
                result = json.getJSONObject("error").getString("message");
                notificationBuilder.setContentTitle(getContext().getString(R.string.notif_add_user_to_board_fail_title))
                .setSmallIcon(R.drawable.ic_notification_failed);


                //Error that is thrown by the server if the user doesn't exist
                switch(result){
                    case "noUser" :
                    notificationBuilder.setContentText(getContext().getString(R.string.notif_user_not_found));
                    Log.i("addUser", "noUser");
                    break;

                    case "alreadyIn" :
                    notificationBuilder.setContentText(getContext().getString(R.string.notif_user_already_on_board));
                    Log.i("addUser", "alreadyIn");
                    break;

                    default:
                    notificationBuilder.setContentText(getContext().getString(R.string.notif_add_user_to_board_fail_text));
                    Log.i("addUser", "fail");
                }

            }
            else {
                //The user has been added to the board
                 notificationBuilder.setContentTitle(getContext().getString(R.string.notif_add_user_to_board_success_title))
                 .setSmallIcon(R.drawable.ic_notification_successful)
                 .setContentText(getContext().getString(R.string.notif_add_user_to_board_success_text));
                Log.i("addUser", "succeed");
            }

            Notification notification;
            //Build doesn't exist before Jelly bean (version 16)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                notification = notificationBuilder.build();
            }
            else {
                notification = notificationBuilder.getNotification();
            }
            NotificationManager mNotifyMgr =
                    (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);
            mNotifyMgr.notify(0, notification);

            /*synchronized (notification){
                notification.notify(0);
            }*/

        }
        catch(JSONException e){
            Toast.makeText(getContext(), getContext().getString(R.string.json_problem_toast), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        catch (Throwable t) {
            Toast.makeText(getContext(), getContext().getString(R.string.toast_an_error_has_occured), Toast.LENGTH_SHORT).show();
            Log.e("My App", "Error " + t.getMessage());
        }
    }
}