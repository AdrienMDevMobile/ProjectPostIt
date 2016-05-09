package complementaryClass;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Design the layout that represents the post it
 */
public abstract class postItTextDesign {
    public static LinearLayout getPostItTextView(Context context, String text, String author, String time){
        LinearLayout layout = new LinearLayout(context);
        Log.i("creationPI", "nous entrons");

        //Contains the text of the post it
        TextView tvText = new TextView(context);
        tvText.setText(text);
        tvText.setTextColor(Color.BLACK);
        layout.addView(tvText);

        TextView tvInfo = new TextView(context);
        tvInfo.setText(author + time);
        tvInfo.setTextColor(Color.GRAY);
        layout.addView(tvInfo);
        Log.i("creationPI", "nous allons retourner");
        return layout;
    }
}
