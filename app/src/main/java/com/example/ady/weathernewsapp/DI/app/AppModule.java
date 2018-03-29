package com.example.ady.weathernewsapp.DI.app;

import com.example.ady.weathernewsapp.Data.Remote.NewsDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ady on 1/12/2018.
 */
@Module
public class AppModule {
    private String baseURL;
    private String APIkey;
    private final String TAG = AppModule.class.getSimpleName();

    public AppModule(String baseURL, String APIkey) {
        this.baseURL = baseURL;
        this.APIkey = APIkey;
    }
    @Provides
    NewsDataSource providesRemoteDataSource(){

        return new NewsDataSource(baseURL,APIkey);

    }
}
