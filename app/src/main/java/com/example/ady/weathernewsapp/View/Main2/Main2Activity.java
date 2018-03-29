package com.example.ady.weathernewsapp.View.Main2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ady.weathernewsapp.AppName;
import com.example.ady.weathernewsapp.Data.Local.NewsLocalDatabase;
import com.example.ady.weathernewsapp.R;
import com.example.ady.weathernewsapp.Util.HelperClasses.News;
import com.example.ady.weathernewsapp.Util.HelperClasses.RecycleViewAdapter;

import java.util.List;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity implements Main2Contrat.View {
    @Inject
    Main2Presenter main2Presenter;
    public static final String TAG = Main2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppName.get(this).getMain2Component().inject(this);
        main2Presenter.attachView(this);
        NewsLocalDatabase newsLocalDatabase = new NewsLocalDatabase(this);
        // any operation only after this
        main2Presenter.getDataBase(newsLocalDatabase,this);
    }

    @Override
    public void ShowError(String messagee) {
    }
    @Override
    public void setDatabse(List<News> Newslist, NewsLocalDatabase newsLocalDatabase) {
        RecyclerView recyclerView = findViewById(R.id.recycleMain2Layout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        RecycleViewAdapter recycleadapter = new RecycleViewAdapter(Newslist,newsLocalDatabase,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(recycleadapter);
    }
}