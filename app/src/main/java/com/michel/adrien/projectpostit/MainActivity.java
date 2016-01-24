package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;

import callAPI.CallAPIBoardList;
import complementaryClass.ActiveBoardInfo;
import complementaryClass.DrawerReader;
import fragment.SettingBoardFragment;

/*
    Main activity.
    Has a side menu with the board list.
 */
public class MainActivity extends AppCompatActivity {


    private Drawer sideMenu = null;
    private JSONArray jsonReader;
    Toolbar toolbar;
    //Remember the Id of the active board.
    private ActiveBoardInfo activeBoardInfo = new ActiveBoardInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Load the settings button
         */
        Button settings = (Button) findViewById(R.id.main_activity_btnSetting);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activeBoardInfo.getActiveBoardId() != "") {
                    SettingBoardFragment settingBoardFragment = SettingBoardFragment.newInstance(activeBoardInfo);
                    settingBoardFragment.show(getSupportFragmentManager(), "");
                }
                else{
                    Toast.makeText(getBaseContext(), R.string.please_select_board, Toast.LENGTH_LONG).show();
                }
            }
        });



        /*
            LOGOUT
         */
        Button logout = (Button) findViewById(R.id.main_activity_btnLogOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences_session), 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(getBaseContext().getString(R.string.sharedPreferences_values_session_token));
                editor.remove(getBaseContext().getString(R.string.sharedPreferences_values_user_id));
                editor.commit();
                //editor.clear();
                Log.i("SharedPreferences", "Cleared");

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        // Handle Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            USE THE ANSWER OF THE SERVER TO MAKE THE BOARD LIST
         */
        String boardList = getIntent().getStringExtra(CallAPIBoardList.INTENT_EXTRA_BOARDS);
        PrimaryDrawerItem listDrawerItem[] = new PrimaryDrawerItem[0];
        try {
            jsonReader = new JSONArray(boardList);
            listDrawerItem = new PrimaryDrawerItem[jsonReader.length()];

            for (int compteur = 0; compteur < jsonReader.length(); compteur++) {
                Log.i("json", jsonReader.getJSONObject(compteur).getString(getString(R.string.json_boardlist_board_name)));
                listDrawerItem[compteur] = new PrimaryDrawerItem().withName(jsonReader.getJSONObject(compteur).getString(getString(R.string.json_boardlist_board_name)));
            }

        } catch (Exception e) {
            Log.e("mainActi", "error in main activity");
            Log.e("mainActi", e.getMessage().toString());
            Toast.makeText(getBaseContext(), getString(R.string.exception_main_activity), Toast.LENGTH_LONG);
        }
        /*
            CREATE THE SIDE MENU
         */
        sideMenu = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(listDrawerItem)
                .inflateMenu(R.menu.main_activity_side_menu)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //The class delegate the click handle to the DrawerReader class.
                        return DrawerReader.onDrawerClick(getBaseContext(), view, position,
                                getSupportFragmentManager(), drawerItem, jsonReader, toolbar, activeBoardInfo);
                    }
                }).build();
    }

}
