package complementaryClass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;

import com.michel.adrien.projectpostit.R;

public class CreateBoardFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.create_board_layout, null))
                .setPositiveButton(R.string.add_board, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dialog dialog2 =Dialog.class.cast(dialog);
                                String boardName = ((EditText)dialog2.
                                        findViewById(R.id.create_board_etBoardName))
                                        .getText().toString();
                                Boolean isPublic = ((CheckBox)dialog2.
                                        findViewById(R.id.create_board_activity_cbIsPublic))
                                            .isChecked();
                            }
                        }
                );
        return builder.create();
    }
}
