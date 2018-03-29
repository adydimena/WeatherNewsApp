package com.example.ady.weathernewsapp.Util.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ady on 1/16/2018.
 */

public class SharedPref {
    private static boolean first = true;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Set<String> favorites;

    private static void initialize(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences("MyPrefNews",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        favorites = sharedPreferences.getStringSet("news",null);
        if(favorites==null)favorites=new HashSet<>();
        first = false;
    }

    public static void commit() {
        if(!first) {
            editor.putStringSet("news",favorites);
            editor.commit();
        }
    }

    public static Set<String> getFavorites(Context context) {
        if(first)initialize(context);
        if(favorites==null)
            favorites = new HashSet<>();
        return favorites;
    }

    public static void addFavorite(String url, Context context) {
        if(first)initialize(context);
        favorites.add(url);
        commit();
    }

    public static void removeFavorite(String url, Context context) {
        if(first)initialize(context);
        if(favorites!=null) {
            if (favorites.contains(url))
                favorites.remove(url);
        }
        else
            favorites = new HashSet<>();
        commit();
    }

    public static void removeAllFavorite(Context context) {
        if(first)initialize(context);
        favorites = new HashSet<>();
        commit();
    }
}
