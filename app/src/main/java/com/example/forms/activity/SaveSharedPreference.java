package com.example.forms.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_EMAIL= "useremail";
    static final String PREF_USER_FULLNAME= "userfullname";
    static final String PREF_USER_MOBILENUM= "usermobilenum";
    static final String PREF_USER_COLLEGENAME= "usercollegename";
    static final String PREF_USER_PASSWORD= "userpassword";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void setUserEmail(Context ctx, String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.commit();
    }

    public static String getUserEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }
    public static void setUserFullname(Context ctx, String name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_FULLNAME, name);
        editor.commit();
    }

    public static String getUserFullname(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_FULLNAME, "");
    }
    public static void setUserMobilenum(Context ctx, String mobileNum)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_MOBILENUM,mobileNum );
        editor.commit();
    }

    public static String getUserMobilenum(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_MOBILENUM, "");
    }
    public static void setUserCollegename(Context ctx, String collegeName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_COLLEGENAME,collegeName );
        editor.commit();
    }

    public static String getUserCollegename(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_COLLEGENAME, "");
    }
    public static void setUserPassword(Context ctx, String userpassword)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PASSWORD,userpassword );
        editor.commit();
    }

    public static String getUserPassword(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PASSWORD, "");
    }



}
