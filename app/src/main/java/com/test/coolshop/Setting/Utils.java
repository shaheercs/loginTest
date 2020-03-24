package com.test.coolshop.Setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Utils {
    private static final String PREFERENCES_FILE = "sample_settings";
    public static String token = "";


    public static void showSnackbar(View view, String message) {
        try {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            // Change the SnackBar default text color
            View snackBarView = snackbar.getView();
            FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
            frameParams.gravity = Gravity.TOP;
            snackBarView.setLayoutParams(frameParams);
            TextView tv = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            // Change the SnackBar default background color
            snackBarView.setBackgroundColor(Color.parseColor("#ff9900"));
            // Display the SnackBar
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }


}
