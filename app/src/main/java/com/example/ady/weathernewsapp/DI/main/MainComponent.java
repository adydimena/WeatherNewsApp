package com.example.ady.weathernewsapp.DI.main;

import com.example.ady.weathernewsapp.View.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Ady on 1/12/2018.
 */

@Subcomponent (modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
