package com.michel.adrien.projectpostit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;

import callAPI.callAPIBoardList;
import complementaryClass.DrawerReadera;

public class MainActivity extends AppCompatActivity  {


    private Drawer sideMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button logout = (Button)findViewById(R.id.main_activity_btnLogOut);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String boardList = getIntent().getStringExtra(callAPIBoardList.INTENT_EXTRA_BOARDS);

        PrimaryDrawerItem listDrawerItem[] = new PrimaryDrawerItem[0];

        /*
        TODO : changer le catch
         */
        try {
            JSONArray jsonReader = new JSONArray(boardList);
            listDrawerItem = new PrimaryDrawerItem[jsonReader.length()];

            for(int compteur=0; compteur<jsonReader.length(); compteur++){
                Log.i("json", jsonReader.getJSONObject(compteur).getString("name"));
                listDrawerItem[compteur] = new PrimaryDrawerItem().withName(jsonReader.getJSONObject(compteur).getString("name"));
            }

        }
        catch(Exception e){
            Log.e("mainActi", "error in main activity");
            Log.i("mainActi", e.getMessage().toString());
        }

        //Create the drawer
        sideMenu = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(listDrawerItem)
               .inflateMenu(R.menu.additional_board_menu)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //The class delegate the click handle to the DrawerReadera class.
                        //new CreateBoardFragment().show(getSupportFragmentManager(), "");
                        return DrawerReadera.onDrawerClick(getBaseContext(), getSupportFragmentManager(), view, position, drawerItem);
                    }
                }).build();

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (sideMenu != null && sideMenu.isDrawerOpen()) {
            sideMenu.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
