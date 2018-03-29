package com.example.ady.weathernewsapp.Data.Remote;

import android.os.Environment;

import com.example.ady.weathernewsapp.Util.pojo.GetNews;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ady on 1/12/2018.
 */

public class NewsDataSource {
    private static String baseURL;
    private static String APIkey;
    private final String TAG = NewsDataSource.class.getSimpleName();
    public NewsDataSource(String baseURL, String APIkey) {
        this.baseURL = baseURL;
        this.APIkey = APIkey;
    }
    private static OkHttpClient httpClientConfig(HttpLoggingInterceptor interceptor){
        // File httpCache = new File(Context.getExternalCacheDir().getAbsolutePath() + "/tile_cache");
        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "HttpCache");// Here to facilitate the file directly on the SD Kagan catalog HttpCache in ， Generally put in context.getCacheDir() in
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return new OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build();
    }
    private static HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return  httpLoggingInterceptor;
    }
    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                //add converter to parse the response
                .client(httpClientConfig(loggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }
    public static Observable<GetNews> getNews(String Topic)
    {
        Retrofit retrofit = create();
        NewsService newsService = retrofit.create(NewsService.class);
        return newsService.getNews(Topic);
    }
}
