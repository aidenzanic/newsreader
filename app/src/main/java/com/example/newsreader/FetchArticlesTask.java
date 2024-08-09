package com.example.newsreader;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class FetchArticlesTask<Article> extends AsyncTask<String, Void, List<Article>> {
    @Override
    protected List<Article> doInBackground(String... params) {
        String query = params[0];
        // Implement your code to fetch articles from API here
        return null; // Replace with actual fetched articles
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        super.onPostExecute(articles);
        // Update your UI with the fetched articles
    }
}
