package fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.michel.adrien.projectpostit.R;

import callAPI.CallAPIAddUserToBoardA;
import complementaryClass.ActiveBoardInfo;

/*
    Fragment where the user can change the settings of the board
    Opened in main activity.
 */
public class SettingBoardFragment extends DialogFragment{

    private static String argumentActiveBoardId = "activeBoardId";
    public View view;
    //The id of the notification is stored here. If multiple

    public static SettingBoardFragment newInstance(ActiveBoardInfo activeBoardInfo){
        SettingBoardFragment settingBoardFragment = new SettingBoardFragment();

        Bundle args = new Bundle();
        args.putString(argumentActiveBoardId, activeBoardInfo.getActiveBoardId());

        settingBoardFragment.setArguments(args);

        return settingBoardFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.setting_board_layout, container, false);
        Button  addUser = (Button)(view.findViewById(R.id.setting_board_btnAddUser));

        Log.i("boardIdAfterButton",  getArguments().getString(argumentActiveBoardId));
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ETUserName = (EditText)view.findViewById(R.id.setting_board_etAddUser);
                String  username = ETUserName.getText().toString();
                new CallAPIAddUserToBoardA(getView().getContext(), getArguments().getString(argumentActiveBoardId), username).execute();
                ETUserName.setText("");
            }
        });
        return view;
    }

}
