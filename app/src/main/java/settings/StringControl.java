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
public abstract class StringControl {

    private static int duration = Toast.LENGTH_SHORT;

    //------------------------------BOOLEAN CONTROL FUNCTIONS------------------------------
    public static boolean checkUsernameLength(Context context, String username){
        if (username.length() < StringControl.getMinLengthUsername()) {
            Log.i("l", "trop court");
            Toast toast = Toast.makeText(context,
                    context.getString(R.string.username_too_short) + StringControl.getMinLengthUsername(), duration);
            toast.show();
            return false;
        }
        if (username.length() > StringControl.getMaxLengthUsername()) {
            Log.i("l", "trop long");
            Toast toast = Toast.makeText(context,
                   context.getString(R.string.username_too_long) + StringControl.getMaxLengthUsername(), duration);
            toast.show();
            return false;
        }
        return true;
    }

    public static boolean checkPasswordLength(Context context, String password){
        if (password.length() < StringControl.getMinLengthPassword()) {
            Log.i("p", "trop court");
            Toast toast = Toast.makeText(context,
                    context.getString(R.string.password_too_short) + StringControl.getMinLengthPassword() , duration);
            toast.show();
            return false;
        }
        if (password.length() > StringControl.getMaxLengthPassword()) {
            Log.i("p", "trop long");
            Toast toast = Toast.makeText(context,
                    context.getString(R.string.password_too_long) + StringControl.getMaxLengthPassword(), duration);
            toast.show();
            return false;
        }
        return true;
    }

    public static boolean checkEmailLength(Context context, String email){
        if (email.length() < StringControl.getMinLengthEmail()) {
            Log.i("m", "trop court");
            Toast toast = Toast.makeText(context,
                    context.getString(R.string.email_too_short) + StringControl.getMinLengthEmail(), duration);
            toast.show();
            return false;
        }
        if (email.length() > StringControl.getMaxLengthEmail()) {
            Log.i("m", "trop long");
            Toast toast = Toast.makeText(context,
                    context.getString(R.string.email_too_long) + StringControl.getMaxLengthEmail(), duration);
            toast.show();
            return false;
        }
        return true;
    }

    /*
    Two functions that check if the email has a valid format (have an @ and a .something
     */
    public static boolean is_Valid_Email(Context context, String email) {
        if (email == null || !isEmailValid(email)) {
            Log.i("m", "format non valide");
            Toast toast = Toast.makeText(context,
                    context.getString(R.string.email_format_not_valid), duration);
            toast.show();
            return false;
        }
        else {
            Log.i("m", "format valide");
            return true;
        }
    }

    private static boolean isEmailValid(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
