package com.example.hp.newsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.newsapp.Model.ResultOfArticle;
import com.example.hp.newsapp.R;

import java.util.ArrayList;

/**
 * Created by hp on 10/29/2017.
 */

public class NewsAdapter extends ArrayAdapter<ResultOfArticle> {
    private Context context;
    private int resource;
    private ArrayList<ResultOfArticle> objects;


    public NewsAdapter(Context context, int resource, ArrayList<ResultOfArticle> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }
        TextView title = convertView.findViewById(R.id.title);
        TextView name = convertView.findViewById(R.id.name);
        TextView author = convertView.findViewById(R.id.author);
        TextView date_published = convertView.findViewById(R.id.date_published);
        ResultOfArticle resultOfArticle = objects.get(position);
        title.setText(resultOfArticle.getWebTitle());
        name.setText(resultOfArticle.getSectionName());
        author.setText(resultOfArticle.getAuthors());
        date_published.setText(resultOfArticle.getWebPublicationDate());
        return convertView;
    }
}
