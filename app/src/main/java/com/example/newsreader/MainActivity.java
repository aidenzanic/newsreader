package com.example.newsreader;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText searchTerm;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArticlesAdapter articlesAdapter;
    private Button viewFavoritesButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private static final String API_KEY = "775b40d0-1fc4-4da2-9051-9ec6d1e3560e";
    private static final String BASE_URL = "https://content.guardianapis.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Force locale to German
        Locale locale = new Locale("de");
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);

        // Initialize UI components
        searchTerm = findViewById(R.id.searchTerm);
        searchButton = findViewById(R.id.searchButton);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        viewFavoritesButton = findViewById(R.id.viewFavoritesButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articlesAdapter = new ArticlesAdapter(this);
        recyclerView.setAdapter(articlesAdapter);

        // Set up Search Button click listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchTerm.getText().toString().trim();
                if (!query.isEmpty()) {
                    new FetchArticlesTask().execute(query);
                } else {
                    Toast.makeText(MainActivity.this, "ERROR WE COULD NOT FIND WHAT YOU ARE LOOKING FOR ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up View Favorites Button click listener
        viewFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
