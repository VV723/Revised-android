package upwork.com.revisedapp.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.regex.Pattern;

import upwork.com.revisedapp.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 29/09/2017.
 */

public class Global {
    public static String BASE_URL1 = "http://192.168.1.111/revised_api/";
    public static String BASE_URL = "http://54.152.137.74/";
    public static final String ERR_SUCCESS = "ok";
    public static final int ERR_FAIL = 0;
    public static final String PREFS_NAME = "RevisedAppPref_NAME";
    public static final String PREFS_PASS = "RevisedAppPref_PASS";
    public static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    public static void ShowErrorDialog(Context context, String title, String content)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(content)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }});
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void SavePreference(Context context, String key, String value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String GetPreference(Context context, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, "");
        if(!value.equalsIgnoreCase(""))
        {
           return value;
        }
        return "";
    }

}
