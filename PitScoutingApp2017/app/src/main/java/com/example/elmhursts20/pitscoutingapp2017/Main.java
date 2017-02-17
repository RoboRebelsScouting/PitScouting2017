package com.example.elmhursts20.pitscoutingapp2017;

import android.app.Application;

/**
 * Created by elmhursts20 on 2/15/2017.
 */

public class Main extends Application {
    public static InfoStorage infoStorage = new InfoStorage();

    private static Main singleton;

    public  static Main getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
