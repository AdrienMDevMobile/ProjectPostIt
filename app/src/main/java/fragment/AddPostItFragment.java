package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.michel.adrien.projectpostit.R;

import callAPI.callAPIAddPostIt;
import complementaryClass.ActiveBoardInfo;
import settings.handParameters;

/**
 * Opens when the user clicks on the Button +
 * Adds a new post to the board
 */
public class AddPostItFragment  extends DialogFragment {

    private static String argumentActiveBoardId = "activeBoardId";
    public View view;

    private static String argumentPIType = "typePI";
    //The id of the notification is stored here. If multiple

    public static AddPostItFragment newInstance(ActiveBoardInfo activeBoardInfo, String addPIType){
        AddPostItFragment addPIFragment = new AddPostItFragment();

        Bundle args = new Bundle();
        args.putString(argumentActiveBoardId, activeBoardInfo.getActiveBoardId());
        args.putString(argumentPIType, addPIType);

        addPIFragment.setArguments(args);

        return addPIFragment;
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //We define the call API here. We executre it in the switch
        final callAPIAddPostIt callAPIAddPostIt = new callAPIAddPostIt(getContext(), argumentPIType);
        //Depending on the type of the new post it,
        switch(getArguments().getString(argumentPIType)) {
            case handParameters.ARG_PI_TYPE_TEXT:
                builder = builder.setView(inflater.inflate(R.layout.add_pi_text_layout, null))
                        .setPositiveButton(getString(R.string.add_post_it_confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dialog dialog2 =Dialog.class.cast(dialog);
                                EditText ETText = (EditText)dialog2.findViewById(R.id.add_pi_text_etText);
                                String  text = ETText.getText().toString();
                                callAPIAddPostIt.execute("type", getArguments().getString(argumentPIType),
                                        "value", text, "boardId", getArguments().getString(argumentActiveBoardId));
                            }
                        });
                break;

        }

        //The negative button is always the same
        builder = builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

}