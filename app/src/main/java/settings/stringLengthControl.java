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
    public static boolean checkLoginLength(Context context, String login){
        if (login.length() < stringLengthControl.getMinLengthLogin()) {
            Log.i("l", "trop court");
            Toast toast = Toast.makeText(context,
                    R.string.login_too_short, duration);
            toast.show();
            return false;
        }
        if (login.length() > stringLengthControl.getMaxLengthLogin()) {
            Log.i("l", "trop long");
            Toast toast = Toast.makeText(context,
                    R.string.login_too_long, duration);
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

    public static boolean checkMailLength(Context context, String mail){
        if (mail.length() < stringLengthControl.getMinLengthMail()) {
            Log.i("m", "trop court");
            Toast toast = Toast.makeText(context,
                    R.string.mail_too_short, duration);
            toast.show();
            return false;
        }
        if (mail.length() > stringLengthControl.getMaxLengthMail()) {
            Log.i("m", "trop long");
            Toast toast = Toast.makeText(context,
                    R.string.mail_too_long, duration);
            toast.show();
            return false;
        }
        return true;
    }


//-----------------LENGTH LIMITATIONS INTS AND GETTERS---------------------------------------
    public static int getMinLengthLogin() {
        return minLengthLogin;
    }


    public static int getMaxLengthLogin() {
        return maxLengthLogin;
    }

    public static int getMinLengthPassword() {
        return minLengthPassword;
    }

    public static int getMaxLengthPassword() {
        return maxLengthPassword;
    }

    public static int getMinLengthMail() {
        return minLengthMail;
    }

    public static int getMaxLengthMail() {
        return maxLengthMail;
    }

    private static int minLengthLogin = 3;

    private static int maxLengthLogin = 15;

    private static int minLengthPassword = 5;

    private static int maxLengthPassword = 20;

    private static int minLengthMail = 3;

    private static int maxLengthMail = 20;

}
