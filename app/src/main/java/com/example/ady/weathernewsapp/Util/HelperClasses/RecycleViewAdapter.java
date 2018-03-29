package com.example.ady.weathernewsapp.Util.HelperClasses;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.ady.weathernewsapp.Data.Local.NewsLocalDatabase;
import com.example.ady.weathernewsapp.R;
import com.example.ady.weathernewsapp.Util.pojo.Article;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ady on 1/15/2018.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    List<Article> list = new ArrayList<>();
    List<News> newsList = new ArrayList<>();
    NewsLocalDatabase newsLocalDatabase;
    Context context;
    boolean pojoObject;
    int click = 0;
    String TAG = RecycleViewAdapter.class.getSimpleName();
    public int getClick()
    {

        return click;
    }
    public void setClick(int click)
    {

        this.click = click;
    }
    public RecycleViewAdapter(List<Article> list, Context contex, NewsLocalDatabase newsLocalDatabase) {
        pojoObject = true;
        this.newsLocalDatabase = newsLocalDatabase;
        this.list = list;
        this.context = contex;
    }
    public RecycleViewAdapter(List<News> newsList, NewsLocalDatabase newsLocalDatabase, Context contex) {
        pojoObject = false;
        this.newsLocalDatabase = newsLocalDatabase;
        this.newsList = newsList;
        this.context = contex;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleviewlayout, null);
        return new ViewHolder(view);//this is not used
        //TODO delete this file when done!! not used
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (pojoObject) {
            holder.title.setText(list.get(position).getTitle());
            holder.description.setText(list.get(position).getDescription());
            Glide.with(context).load(list.get(position).getUrlToImage()).into(holder.image);
            holder.author.setText("Author: " + list.get(position).getAuthor());
            if (newsLocalDatabase.isDataExists(list.get(position).getUrlToImage())) {
                holder.btnDataBase.setBackgroundColor(context.getColor(R.color.colorAccent));
            }
            if (SharedPref.getFavorites(context).contains(list.get(position).getUrlToImage())) {
                holder.btnSharedPref.setBackgroundColor(context.getColor(R.color.colorAccent));
            }
            holder.btnDataBase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (newsLocalDatabase.isDataExists(list.get(position).getUrlToImage())) {
                        boolean isdeleted = newsLocalDatabase.deleterow(list.get(position).getUrlToImage());
                        if (isdeleted)
                            Toast.makeText(context, "Removed from DataBase", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(context, "NOT Removed from DataBase", Toast.LENGTH_LONG).show();
                        holder.btnDataBase.setBackgroundColor(context.getColor(R.color.colorPrimary));
                    } else {
                        boolean isInserted = newsLocalDatabase.insertData(list.get(position).getDescription(),
                                list.get(position).getAuthor(),
                                list.get(position).getTitle(),
                                list.get(position).getUrlToImage());
                        if (isInserted) {
                            Toast.makeText(context, "inserted to the Database", Toast.LENGTH_LONG).show();
                            holder.btnDataBase.setBackgroundColor(context.getColor(R.color.colorAccent));
                        } else {
                            Toast.makeText(context, "not inserted to the DataBase", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            holder.btnSharedPref.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SharedPref.getFavorites(context).contains(list.get(position).getUrlToImage())) {
                        SharedPref.removeFavorite(list.get(position).getUrlToImage(), context);
                        holder.btnSharedPref.setBackgroundColor(context.getColor(R.color.colorPrimary));
                        Toast.makeText(context, "Removed from Shared Pref", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPref.addFavorite(list.get(position).getUrlToImage(), context);
                        holder.btnSharedPref.setBackgroundColor(context.getColor(R.color.colorAccent));
                        Toast.makeText(context, "Added from Shared Pref", Toast.LENGTH_LONG).show();
                    }
                }
            });
            holder.linearlayoutbtnclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context,"you have clicked on "+ list.get(position).getTitle(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(context,"you have clicked on "+ list.get(position).getTitle(),Toast.LENGTH_LONG).show();
                    //Log.d(TAG, "onClick: " + list.get(position).getUrlToImage() );
                    //Intent intent = new Intent(context,Main2Activity.class);
                    //intent.putExtra("magic",list.get(position).getUrlToImage());
                    //context.startActivity(intent);
                }
            });
        }else{ // Second Object
            holder.title.setText(newsList.get(position).getTitle());
            holder.description.setText(newsList.get(position).getDiscri());
            Glide.with(context).load(newsList.get(position).getImageUrl()).into(holder.image);
            holder.author.setText("Author: "+newsList.get(position).getAuthor());
            if(newsLocalDatabase.isDataExists(newsList.get(position).getImageUrl())){
                holder.btnDataBase.setBackgroundColor(context.getColor(R.color.colorAccent));
            }
            if(SharedPref.getFavorites(context).contains(newsList.get(position).getImageUrl())){
                holder.btnSharedPref.setBackgroundColor(context.getColor(R.color.colorAccent));
            }
            holder.btnDataBase.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(newsLocalDatabase.isDataExists(newsList.get(position).getImageUrl())){
                        boolean isdeleted = newsLocalDatabase.deleterow(newsList.get(position).getImageUrl());
                        if(isdeleted) {
                            newsList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,newsList.size());
                            Toast.makeText(context, "Removed from DataBase", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(context, "NOT Removed from DataBase", Toast.LENGTH_LONG).show();
                        holder.btnDataBase.setBackgroundColor(context.getColor(R.color.colorPrimary));
                    }
                    else {
                        boolean isInserted = newsLocalDatabase.insertData(newsList.get(position).getDiscri(),
                                newsList.get(position).getAuthor(),
                                newsList.get(position).getTitle(),
                                newsList.get(position).getImageUrl());
                        if (isInserted) {
                            Toast.makeText(context, "inserted to the Database", Toast.LENGTH_LONG).show();
                            holder.btnDataBase.setBackgroundColor(context.getColor(R.color.colorAccent));
                        } else {
                            Toast.makeText(context, "not inserted to the DataBase", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            holder.btnSharedPref.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(SharedPref.getFavorites(context).contains(newsList.get(position).getImageUrl())){
                        SharedPref.removeFavorite(newsList.get(position).getImageUrl(),context);
                        holder.btnSharedPref.setBackgroundColor(context.getColor(R.color.colorPrimary));
                        Toast.makeText(context, "Removed from Shared Pref", Toast.LENGTH_LONG).show();
                    }
                    else {
                        SharedPref.addFavorite(newsList.get(position).imageUrl, context);
                        holder.btnSharedPref.setBackgroundColor(context.getColor(R.color.colorAccent));
                        Toast.makeText(context, "Added from Shared Pref", Toast.LENGTH_LONG).show();
                    }
                }
            });
            holder.linearlayoutbtnclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
    @Override
    public int getItemCount() {
        if(pojoObject)
            return list.size();
        else
            return newsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,author;
        ImageView image;
        LinearLayout linearlayoutbtnclick;
        Button btnDataBase,btnSharedPref;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.imageTitle);
            description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.author);
            image = itemView.findViewById(R.id.picImaage);
            linearlayoutbtnclick = itemView.findViewById(R.id.linearlayoutbtnclick);
            btnDataBase= itemView.findViewById(R.id.btndatabase);
            btnSharedPref = itemView.findViewById(R.id.btSharedPreference);
        }
    }
}