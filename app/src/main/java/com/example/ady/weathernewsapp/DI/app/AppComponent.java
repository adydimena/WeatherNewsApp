package com.example.ady.weathernewsapp.DI.app;

import com.example.ady.weathernewsapp.DI.main.MainComponent;
import com.example.ady.weathernewsapp.DI.main.MainModule;
import com.example.ady.weathernewsapp.DI.main2.Main2Component;
import com.example.ady.weathernewsapp.DI.main2.Main2Module;

import dagger.Component;

/**
 * Created by Ady on 1/12/2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    MainComponent add(MainModule mainModule);
    Main2Component add(Main2Module main2Module);

}
