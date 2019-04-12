package com.example.move;

import android.content.res.Configuration;

import com.orm.SugarApp;
import com.orm.SugarContext;

public class SugarORMDatabase extends SugarApp {

    private static String nomUser = "";

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public String getNomUser(){
        return nomUser;
    }

    public void setNomUser(String nom){
        this.nomUser = nom;
    }
}
