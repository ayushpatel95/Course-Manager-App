package com.example.jarvis.myapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by jarvis on 2/11/17.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

// Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

// Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

// Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(getApplicationContext())
        );

// Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

// Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }
}
