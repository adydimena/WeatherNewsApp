package com.example.ady.weathernewsapp.View.main;

import com.example.ady.weathernewsapp.Data.Local.NewsLocalDatabase;
import com.example.ady.weathernewsapp.Data.Remote.NewsDataSource;
import com.example.ady.weathernewsapp.Util.pojo.Article;
import com.example.ady.weathernewsapp.Util.pojo.GetNews;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ady on 1/14/2018.
 */

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    NewsDataSource newsDataSource;
    GetNews dummyGetnews = null;

    @Inject
    public MainPresenter(NewsDataSource newsDataSource){
        this.newsDataSource = newsDataSource;
    }

    public MainPresenter() {
        //
    }


    @Override
    public void attachView(MainContract.View view) {
        this.view = view;

    }

    @Override
    public void detachView() {
        this.view = null;

    }

    @Override
    public void getMessage(String message) {

        view.setMessage(message);

    }


    @Override
    public void getNews(String topic, final NewsLocalDatabase newsLocalDatabase) {
        NewsDataSource.getNews(topic)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetNews>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNews getNews) {
                        dummyGetnews = getNews;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        List<Article> articles = new ArrayList<>();
                        if (dummyGetnews.getArticles() != null) {
                            articles = dummyGetnews.getArticles();
                            view.setNews(articles,newsLocalDatabase);

                        }


                    }
                });

    }
}
