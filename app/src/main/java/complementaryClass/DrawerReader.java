package complementaryClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.michel.adrien.projectpostit.LoginActivity;
import com.michel.adrien.projectpostit.R;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import callAPI.CallAPIGetPostItList;
import fragment.CreateBoardFragment;

/**
 * Abstract class that is called when the user clicks on element of the side list.
 */
public abstract class DrawerReader {

    public static boolean onDrawerClick(Context context,  View view, int position,
                                        FragmentManager fragmentManager,  IDrawerItem drawerItem,
                                        JSONArray jsonArray, Toolbar toolbar, ActiveBoardInfo activeBoardInfo, LinearLayout currentLayout){
        if (drawerItem instanceof Nameable) {
            String name = ((Nameable) drawerItem).getName().getText(context);
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

            //If the user clicked on a link toward a board. If we don't check that jsonArray is not null, the application crash if we are offline
            if ( jsonArray != null && position < jsonArray.length()) {
                Log.i("positionClick", Integer.toString(position));
                try {
                    if(currentLayout == null){
                        Log.w("PROBLEME MAJEUR", "PROBLEME MAJEUR");
                    }
                    return onBoardNameClick(context, jsonArray.getJSONObject(position), toolbar,
                            activeBoardInfo, currentLayout);
                }
                catch(JSONException e){
                    Toast.makeText(context, context.getString(R.string.exception_json), Toast.LENGTH_LONG).show();
                    Log.e("onDrawerClick", "jsonProblem");
                    return false;
                }
            }

            if(name.equals(context.getString(R.string.add_board))){
                return onAddBoardClick(context, fragmentManager);
            }
            if (name.equals(context.getString(R.string.logout))){
                return logOutClick(context);
            }
        }

        return false;
    }

    public static boolean onBoardNameClick(Context context, JSONObject jsonObject,
                                           Toolbar toolbar, ActiveBoardInfo activeBoardInfo, LinearLayout currentLayout) throws JSONException{
        toolbar.setTitle(jsonObject.getString(context.getString(R.string.json_boardlist_board_name)));
        activeBoardInfo.setActiveBoardId(jsonObject.getString(context.getString(R.string.json_boardlist_board_id)));
        if(currentLayout == null){
            Log.w("PROBLEME", "PROBLEME");
        }
        CallAPIGetPostItList callAPIGetPostItList = new CallAPIGetPostItList(context, currentLayout);
        callAPIGetPostItList.execute("boardId", activeBoardInfo.getActiveBoardId());
        return false;
    }

    public static boolean onAddBoardClick(Context context, FragmentManager fragmentManager){
        Log.i("onAddBoardClick", "on va lancer le fragment");
        Toast.makeText(context, "youpi", Toast.LENGTH_SHORT).show();
        new CreateBoardFragment().show(fragmentManager, "");
        return false;
    }

    public static boolean logOutClick(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharedPreferences_session), 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(context.getString(R.string.sharedPreferences_values_session_token));
        editor.remove(context.getString(R.string.sharedPreferences_values_user_id));
        editor.commit();
        //editor.clear();
        Log.i("SharedPreferences", "Cleared");

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        return  false;
    }
}
