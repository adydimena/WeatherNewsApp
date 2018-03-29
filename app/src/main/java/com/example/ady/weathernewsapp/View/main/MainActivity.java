package com.example.ady.weathernewsapp.View.main;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ady.weathernewsapp.AppName;
import com.example.ady.weathernewsapp.Data.Local.NewsLocalDatabase;
import com.example.ady.weathernewsapp.R;
import com.example.ady.weathernewsapp.Util.HelperClasses.RecycleViewAdapter;
import com.example.ady.weathernewsapp.Util.pojo.Article;
import com.example.ady.weathernewsapp.View.Main2.Main2Activity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @Inject
    MainPresenter mainPresenter;
    TextView textView;
    private DrawerLayout dlayout;
    private ActionBarDrawerToggle btnToggle;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppName.get(this).getMainComponent().inject(this);
        mainPresenter.attachView(this);
        NewsLocalDatabase newsLocalDatabase = new NewsLocalDatabase(this);
        // any operation after this....
        setNaviBar();
        textView = findViewById(R.id.tvtextview);
        mainPresenter.getMessage("THIS IS THE ARCHIITECTURE FOR THE WEATHER/NEWS APP CREATED BY ADILSON BARBOSA");
        mainPresenter.getNews("sports", newsLocalDatabase);
    }
    public void database(MenuItem item)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void sharedpref(MenuItem item)
    {
        Toast.makeText(this, "Shared Pref Cliqued", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(btnToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void setNaviBar() {
        dlayout = findViewById(R.id.drawer_layout);
        btnToggle = new ActionBarDrawerToggle(this,dlayout, R.string.Open,R.string.Close);
        dlayout.addDrawerListener(btnToggle);
        btnToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void ShowError(String messagee) {
        Toast.makeText(this, "ERROR " + messagee, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setMessage(String message) {
        textView.setText(message);
    }

    @Override
    public void setNews(List<Article> articles, NewsLocalDatabase newsLocalDatabase) {
        RecyclerView recyclerView = findViewById(R.id.recycleMainLayout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        RecycleViewAdapter recycleadapter = new RecycleViewAdapter(articles, this, newsLocalDatabase);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(recycleadapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}