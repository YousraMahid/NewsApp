package com.example.hp.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.newsapp.Adapter.NewsAdapter;
import com.example.hp.newsapp.Model.ResultOfArticle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    ListView listView;
    TextView total;
    String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        total = (TextView) findViewById(R.id.total_view);

            getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

                getSupportLoaderManager().restartLoader(0, null, MainActivity.this).forceLoad();
                Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new AsyncTask(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null && !data.isEmpty())
            updateUI(data);
        else
            Toast.makeText(MainActivity.this, "no internet", Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String data) {
        try {
            final ArrayList<ResultOfArticle> results = new ArrayList<>();

            JSONObject root = new JSONObject(data);
            JSONObject secondObject = root.getJSONObject("response");
            int totalItem;
            if (secondObject.has("total")) {
                totalItem = secondObject.getInt("total");
                total.setText("About "+String.valueOf(totalItem)+" results");
            }

            JSONArray firstArray = secondObject.getJSONArray("results");
            for (int i = 0; i < firstArray.length(); i++) {
                JSONObject objectInArray = firstArray.getJSONObject(i);
                String sectionName;
                if (objectInArray.has("sectionName")) {
                    sectionName = objectInArray.getString("sectionName");
                } else sectionName = null;
                String webPublicationDate;
                if (objectInArray.has("webPublicationDate"))
                    webPublicationDate = objectInArray.getString("webPublicationDate");
                else webPublicationDate = null;
                String webTitle;
                if (objectInArray.has("webTitle"))
                    webTitle = objectInArray.getString("webTitle");
                else webTitle = null;
                if (objectInArray.has("webUrl"))
                    webUrl = objectInArray.getString("webUrl");
                else webUrl = null;
                String byline;
                if(objectInArray.has("fields")) {
                    JSONObject lastObject = objectInArray.getJSONObject("fields");
                    if (lastObject.has("byline"))
                        byline = lastObject.getString("byline");
                    else byline = null;
                } else
                    byline = null;
                ResultOfArticle resultOfArticle = new ResultOfArticle(webTitle, sectionName, byline, webPublicationDate,webUrl);
                results.add(resultOfArticle);

            }
            NewsAdapter adapter = new NewsAdapter(this, R.layout.item_list, results);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ResultOfArticle news = results.get(position);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(news.getWebUrl()));
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
