package com.example.newsreader;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteArticle.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract FavoriteArticleDao favoriteArticleDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Use Room to create the database instance
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "newsreader-db")
                            .fallbackToDestructiveMigration() // Optional: Allows migration by destroying and recreating the database
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
