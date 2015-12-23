package settings;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

/**
 * Contains the variable of the size limitations and their getter
 * Also there are the booleans functions that checks if the lengths are respected.
 * A toast is shown if not.
 */
public abstract class stringLengthControl {

    private static int duration = Toast.LENGTH_SHORT;

    //------------------------------BOOLEAN CONTROL FUNCTIONS------------------------------
    public static boolean checkUsernameLength(Context context, String username){
        if (username.length() < stringLengthControl.getMinLengthUsername()) {
            Log.i("l", "trop court");
            Toast toast = Toast.makeText(context,
                    R.string.username_too_short, duration);
            toast.show();
            return false;
        }
        if (username.length() > stringLengthControl.getMaxLengthUsername()) {
            Log.i("l", "trop long");
            Toast toast = Toast.makeText(context,
                    R.string.username_too_long, duration);
            toast.show();
            return false;
        }
        return true;
    }

    public static boolean checkPasswordLength(Context context, String password){
        if (password.length() < stringLengthControl.getMinLengthPassword()) {
            Log.i("p", "trop court");
            Toast toast = Toast.makeText(context,
                    R.string.password_too_short, duration);
            toast.show();
            return false;
        }
        if (password.length() > stringLengthControl.getMaxLengthPassword()) {
            Log.i("p", "trop long");
            Toast toast = Toast.makeText(context,
                    R.string.password_too_long, duration);
            toast.show();
            return false;
        }
        return true;
    }

    public static boolean checkEmailLength(Context context, String mail){
        if (mail.length() < stringLengthControl.getMinLengthEmail()) {
            Log.i("m", "trop court");
            Toast toast = Toast.makeText(context,
                    R.string.mail_too_short, duration);
            toast.show();
            return false;
        }
        if (mail.length() > stringLengthControl.getMaxLengthEmail()) {
            Log.i("m", "trop long");
            Toast toast = Toast.makeText(context,
                    R.string.mail_too_long, duration);
            toast.show();
            return false;
        }
        return true;
    }


//-----------------LENGTH LIMITATIONS INTS AND GETTERS---------------------------------------
    public static int getMinLengthUsername() {
        return minLengthUsername;
    }


    public static int getMaxLengthUsername() {
        return maxLengthUsername;
    }

    public static int getMinLengthPassword() {
        return minLengthPassword;
    }

    public static int getMaxLengthPassword() {
        return maxLengthPassword;
    }

    public static int getMinLengthEmail() {
        return minLengthEmail;
    }

    public static int getMaxLengthEmail() {
        return maxLengthEmail;
    }

    private static int minLengthUsername = 3;

    private static int maxLengthUsername = 15;

    private static int minLengthPassword = 5;

    private static int maxLengthPassword = 20;

    private static int minLengthEmail = 3;

    private static int maxLengthEmail = 20;

}
