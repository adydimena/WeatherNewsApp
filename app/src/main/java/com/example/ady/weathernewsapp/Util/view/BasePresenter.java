package com.example.ady.weathernewsapp.Util.view;

/**
 * Created by Ady on 1/14/2018.
 */

public interface BasePresenter <V extends BaseView>  {
    void attachView (V view);
    void detachView();
}
