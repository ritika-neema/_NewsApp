package com.ritikaneema.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        super(context, 0, newsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        News news = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        TextView titleTV = convertView.findViewById(R.id.title);
        TextView authorTV = convertView.findViewById(R.id.author);
        TextView dateTV = convertView.findViewById(R.id.date);

        titleTV.setText(news.getTitle());
        authorTV.setText(news.getAuthor());
        dateTV.setText(news.getDate());

        return convertView;
    }
}
