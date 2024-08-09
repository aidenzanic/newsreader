package com.example.newsreader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoriteArticlesFragment extends Fragment {

    private RecyclerView recyclerViewFavorites;
    private FavoriteArticlesAdapter favoriteArticlesAdapter;
    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_article, container, false);

        recyclerViewFavorites = view.findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        db = AppDatabase.getInstance(getContext());

        // Initialize the adapter with an empty list initially
        favoriteArticlesAdapter = new FavoriteArticlesAdapter(new ArrayList<>(), getContext());
        recyclerViewFavorites.setAdapter(favoriteArticlesAdapter);

        // Load favorites in background thread
        new LoadFavoritesTask().execute();

        return view;
    }

    // Define the AsyncTask as an inner class within the fragment
    private class LoadFavoritesTask extends AsyncTask<Void, Void, List<FavoriteArticle>> {
        @Override
        protected List<FavoriteArticle> doInBackground(Void... voids) {
            return db.favoriteArticleDao().getAllFavorites();
        }

        @Override
        protected void onPostExecute(List<FavoriteArticle> favoriteArticles) {
            favoriteArticlesAdapter.setFavorites(favoriteArticles);
        }
    }
}
