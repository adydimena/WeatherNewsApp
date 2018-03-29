package com.example.ady.weathernewsapp.Data.Remote;

import com.example.ady.weathernewsapp.Util.pojo.GetNews;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ady on 1/12/2018.
 */

public interface NewsService {
    @GET("top-headlines?apiKey=5c3f38ea67d34463b56c92efc0c8a0f4")
    io.reactivex.Observable<GetNews> getNews(@Query("category") String Topic);
}
