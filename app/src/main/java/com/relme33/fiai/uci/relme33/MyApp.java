package com.relme33.fiai.uci.relme33;

import android.app.Application;
import com.relme33.fiai.uci.relme33.database.DatabaseHelper;

import java.util.ArrayList;

public class MyApp extends Application {

    private DatabaseHelper dbHelper;

    @Override
    public void onCreate() {
        dbHelper = new DatabaseHelper(this);
        dbHelper.openForWriting();

        super.onCreate();
    }

    @Override
    public void onTerminate() {
        dbHelper.close();
        super.onTerminate();
    }

}
