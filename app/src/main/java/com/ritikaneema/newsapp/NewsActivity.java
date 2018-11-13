package com.ritikaneema.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String NEWS_REQUEST_URL =
            "\n" +
                    "https://content.guardianapis.com/search?section=world&order-by=newest&api-key=9b98755e-20bb-4de3-be54-d46e6a6ce73b";
    private static final String LOG_TAG = NewsActivity.class.getSimpleName();
    private static final int NEWS_LOADER_ID = 1;
    ProgressBar loadingIndicator;
    TextView emptyView;
    ListView newsListView;
    private NewsAdapter mNewsAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyView = findViewById(R.id.empty_view);
        emptyView.setText(getResources().getText(R.string.no_news));

        newsListView = findViewById(R.id.newsListView);
        newsListView.setEmptyView(emptyView);

        mNewsAdapters = new NewsAdapter(this, new ArrayList<News>());

        newsListView.setAdapter(mNewsAdapters);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mNewsAdapters.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getWebUrl());
                Log.d(LOG_TAG, newsUri.toString());

                if (currentNews.getWebUrl() == null || TextUtils.isEmpty(currentNews.getWebUrl())) {
                    Toast.makeText(NewsActivity.this, NewsActivity.this.getResources().getString(R.string.no_link_found), Toast.LENGTH_SHORT).show();
                } else {
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(websiteIntent);
                }
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            emptyView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        emptyView.setText(R.string.no_news);

        mNewsAdapters.clear();

        if (data != null && !data.isEmpty()) {
            mNewsAdapters.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapters.clear();
    }

}
