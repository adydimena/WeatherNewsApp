package com.example.ady.weathernewsapp.DI.main2;

import com.example.ady.weathernewsapp.View.Main2.Main2Activity;

import dagger.Subcomponent;

/**
 * Created by Ady on 1/17/2018.
 */

@Subcomponent (modules = Main2Module.class)
public interface Main2Component {
    void inject(Main2Activity main2Activity);
}
