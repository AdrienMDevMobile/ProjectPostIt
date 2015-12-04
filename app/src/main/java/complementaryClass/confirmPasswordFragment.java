package complementaryClass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;

/**
 * Created by Adrien on 15/11/2015.
 */
public class confirmPasswordFragment extends DialogFragment {

    public static String argumentPassword = "passwordToCheck";

    public static confirmPasswordFragment newInstance(String passwordToCheck) {
        confirmPasswordFragment confirmPasswordFragment = new confirmPasswordFragment();

        Bundle args = new Bundle();
        args.putString(argumentPassword, passwordToCheck);
        confirmPasswordFragment.setArguments(args);

        return confirmPasswordFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.confirm_password_layout, null))
                // Add action buttons
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog dialog2 =Dialog.class.cast(dialog);
                        String confirmedPassword = ((EditText)dialog2.
                                findViewById(R.id.confirm_password_layout_etPassword))
                                .getText().toString();
                        //C'est bon
                        if(confirmedPassword.equals(getArguments().getString(argumentPassword))){
                            Toast toast = Toast.makeText(getActivity().getBaseContext(),
                                    "C'est bon", Toast.LENGTH_SHORT);
                            toast.show();

                            /*Call of the API

                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("login", login));
                            params.add(new BasicNameValuePair("password", password));

                            serviceHandler.makeServiceCall(apiUrl.getUserRegisterRoute(), 1, params); */
                        }
                        else {
                            Toast toast = Toast.makeText(getActivity().getBaseContext(),
                                    R.string.password_confirm_wrong, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    }
