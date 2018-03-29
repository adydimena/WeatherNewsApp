package com.example.ady.weathernewsapp;

import android.app.Application;
import android.content.Context;

import com.example.ady.weathernewsapp.DI.app.AppComponent;
import com.example.ady.weathernewsapp.DI.app.AppModule;
import com.example.ady.weathernewsapp.DI.app.DaggerAppComponent;
import com.example.ady.weathernewsapp.DI.main.MainComponent;
import com.example.ady.weathernewsapp.DI.main.MainModule;
import com.example.ady.weathernewsapp.DI.main2.Main2Component;
import com.example.ady.weathernewsapp.DI.main2.Main2Module;
import com.example.ady.weathernewsapp.Util.HelperClasses.SharedPref;

/**
 * Created by Ady on 1/14/2018.
 */

public class AppName extends Application {
    private static final String baseURl = "https://newsapi.org/v2/";
    public static final String APIkey = "5c3f38ea67d34463b56c92efc0c8a0f4";
    public static final String TAG = AppName.class.getSimpleName();
    private AppComponent appComponent;
    private MainComponent mainComponent;
    private Main2Component main2Component;

    @Override
    public void onCreate() {
        super.onCreate();
        AppModule appModule = new AppModule(baseURl,APIkey);
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
    }
    public static AppName get(Context context){

        return (AppName) context.getApplicationContext();
    }
    public MainComponent getMainComponent(){
        mainComponent = appComponent.add(new MainModule());
        return  mainComponent;
    }
    public Main2Component getMain2Component(){
        main2Component = appComponent.add(new Main2Module());
        return main2Component;
    }
    public void ClearMainCoponent(){
        mainComponent = null;
    }
    public void ClearMain2Coponent(){
        main2Component = null;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SharedPref.commit();
    }
}
