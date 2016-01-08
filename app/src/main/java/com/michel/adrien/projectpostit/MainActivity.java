package com.michel.adrien.projectpostit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import org.json.JSONArray;

import callAPI.callAPIBoardList;

public class MainActivity extends AppCompatActivity  {


    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String boardList = getIntent().getStringExtra(callAPIBoardList.INTENT_EXTRA_BOARDS);
        /*
        TODO : changer le catch
         */
        try {
            JSONArray jsonReader = new JSONArray(boardList);
            for(int compteur=0; compteur<jsonReader.length(); compteur++){
                Log.i("json", jsonReader.getJSONObject(compteur).getString("name"));
            }
            Log.i("mainActi", boardList);
        }
        catch(Exception e){
            Log.e("mainActi", "error in main activity");
            Log.i("mainActi", e.getMessage().toString());
        }

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                        //.withToolbar(toolbar)
                .inflateMenu(R.menu.example_menu)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                        }

                        return false;
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
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
