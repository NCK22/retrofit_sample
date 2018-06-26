package com.applex.matrimony.Storage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SPCustProfile{

    public static SPCustProfile spInstance;
    public SharedPreferences preferences;
    public String prefName = "profdtl";

    public SPCustProfile(Context context)
    {
        preferences = context.getSharedPreferences(prefName, 0);
        spInstance=this;
    }


    public static synchronized SPCustProfile getSpInstance() {
        return spInstance;
    }


    public String getProfile_id() {

        return preferences.getString("profile_id", "");
    }
    public String getUser_id() {

        return preferences.getString("user_id", "");
    }

    public String getReligion() {
      //  preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("religion", "");
    }

    public String getGender() {
        //preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("gender", "");
    }

    public String getDob() {
        //preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("dob", "");
    }

    public String getMobile() {
        //preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("mobile", "");
    }

    public String getEmail() {
       // preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("email", "");
    }

    public String getMatrimonyId() {
        // preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("matrimony_id", "");
    }

    public String getShortListedProf() {
        // preferences = this.getSharedPreferences(prefName, 0);
        return preferences.getString("shortlistMy", "");
    }
    public void setShortListedProf(String shortlistMy) {
        //preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("shortlistMy", shortlistMy);
        editor.apply();
    }

    public void setReligion(String religion) {
        //preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("religion", religion);
        editor.apply();
    }

    public void setProfile_id(String profile_id) {
       // preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("profile_id", profile_id);
        editor.apply();
    }

    public void setUser_id(String user_id) {
        // preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", user_id);
        editor.apply();
    }

    public void setGender(String gender) {
        //preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("gender", gender);
        editor.apply();
    }

    public void setDob(String dob) {
        //preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("dob", dob);
        editor.apply();
    }

    public void setMobile(String mobile) {
       // preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile", mobile);
        editor.apply();
    }

    public void setEmail(String email) {
        //preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.apply();
    }

    public void setMatrimonyId(String matrimony_id) {
        //preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("matrimony_id", matrimony_id);
        editor.apply();
    }
}
