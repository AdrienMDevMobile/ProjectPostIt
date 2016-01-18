package complementaryClass;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.michel.adrien.projectpostit.R;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

/**
 * Created by Adrien on 18/01/2016.
 */
public class DrawerReadera {

    public static boolean onDrawerClick(Context context, FragmentManager fragmentManager,  View view, int position, IDrawerItem drawerItem){
        if (drawerItem instanceof Nameable) {
            String name = ((Nameable) drawerItem).getName().getText(context);
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

            Log.i("fragmentAddBoard", "avantLaCondition");
            if(name == context.getString(R.string.add_board)){
                return onAddBoardClick(context, fragmentManager);
            }
        }

        return false;
    }

    public static boolean onAddBoardClick(Context context, FragmentManager fragmentManager){
        Toast.makeText(context, "youpi", Toast.LENGTH_SHORT).show();
        Log.i("fragmentAddBoard", "avantLeShow");
        new CreateBoardFragmenta().show(fragmentManager, "");
        Log.i("fragmentAddBoard", "apresLeShow");
        return false;
    }
}
