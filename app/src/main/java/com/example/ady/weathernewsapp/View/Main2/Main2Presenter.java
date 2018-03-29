package com.example.ady.weathernewsapp.View.Main2;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.ady.weathernewsapp.Data.Local.NewsLocalDatabase;
import com.example.ady.weathernewsapp.Data.Remote.NewsDataSource;
import com.example.ady.weathernewsapp.Util.HelperClasses.News;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ady on 1/17/2018.
 */

public class Main2Presenter implements Main2Contrat.Presenter {
    Main2Contrat.View view;
    NewsDataSource newsDataSource;
    public static final String TAG = Main2Presenter.class.getSimpleName();

    @Inject
    public Main2Presenter(NewsDataSource newsDataSource){
        this.newsDataSource = newsDataSource;
    }

    public Main2Presenter() {
    }

    @Override
    public void attachView(Main2Contrat.View view) {
        this.view = view;
    }
    @Override
    public void detachView() {
        this.view = null;
    }


    @Override
    public void getDataBase(NewsLocalDatabase newsLocalDatabase, Context context) {
        int i =0;
        List<News> NewsList = new ArrayList<>();
        Cursor result = newsLocalDatabase.getallData();
        if (result.getCount() == 0){
            Toast.makeText(context, "DataBase empty", Toast.LENGTH_SHORT).show();
        }else{
            while (result.moveToNext()){
               // newsdataforDatabase.get(i).setDescription(result.getString(0));
               // newsdataforDatabase.get(i).setAuthor(result.getString(1));
               // newsdataforDatabase.get(i).setTitle(result.getString(2));
               // newsdataforDatabase.get(i).setUrlToImage(result.getString(3));
               // i++;
                NewsList.add(new News(result.getString(0),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3)));

                Log.d(TAG, "getDataBase: Descr " + result.getString(0));
                Log.d(TAG, "getDataBase: Author " + result.getString(1));
                Log.d(TAG, "getDataBase: Title " + result.getString(2));
                Log.d(TAG, "getDataBase: ImageUrl " + result.getString(3));

            }
            for (int j = 0; j <NewsList.size() ; j++) {
                Log.d(TAG, "getDataBase: "+NewsList.get(j).getDiscri()
                +"\n"+NewsList.get(j).getAuthor()
                +"\n"+NewsList.get(j).getTitle()
                +"\n"+NewsList.get(j).getImageUrl());
            }
            view.setDatabse(NewsList,newsLocalDatabase);
        }


    }
}
