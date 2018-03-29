package com.example.ady.weathernewsapp.View.main;

import com.example.ady.weathernewsapp.Data.Local.NewsLocalDatabase;
import com.example.ady.weathernewsapp.Util.view.BasePresenter;
import com.example.ady.weathernewsapp.Util.view.BaseView;
import com.example.ady.weathernewsapp.Util.pojo.Article;

import java.util.List;

/**
 * Created by Ady on 1/14/2018.
 */

public interface MainContract {


    // methods for the main Activity
    interface View extends BaseView {
        void setMessage(String message);
        void setNews(List<Article> articles,NewsLocalDatabase newsLocalDatabase);
    }
    interface Presenter extends BasePresenter<View>{
        void getMessage(String message);
        void getNews(final String Topic, NewsLocalDatabase newsLocalDatabase);
    }
}
