package com.example.ady.weathernewsapp.DI.main2;

import com.example.ady.weathernewsapp.Data.Remote.NewsDataSource;
import com.example.ady.weathernewsapp.View.Main2.Main2Presenter;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Ady on 1/17/2018.
 */

@Module
public class Main2Module {
    @Provides
    Main2Presenter providesMain2Presenter (NewsDataSource newsDataSource){
        return new Main2Presenter(newsDataSource);
    }
}
