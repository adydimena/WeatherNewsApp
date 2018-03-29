package com.example.ady.weathernewsapp.DI.main;

import com.example.ady.weathernewsapp.Data.Remote.NewsDataSource;
import com.example.ady.weathernewsapp.View.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ady on 1/12/2018.
 */

@Module
public class MainModule {
    @Provides
    MainPresenter providesMainPresenter (NewsDataSource newsDataSource){
        return new MainPresenter(newsDataSource);
    }
}
