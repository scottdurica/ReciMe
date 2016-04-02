package com.emrox_riprap.recime;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by scott on 12/6/2015.
 */
public class Utilities {

    public static boolean validateStringFields(String []values){
        for (String s: values){
            if (s.trim().length()==0){
                return false;
            }
        }
        return true;

    }
    public static boolean validateStringField(String string){
        if (string.trim().length()>0){
            return true;
        }
        return false;
    }
    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken() , 0);
    }
}
