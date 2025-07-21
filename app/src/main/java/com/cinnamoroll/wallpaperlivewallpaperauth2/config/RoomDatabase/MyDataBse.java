package com.cinnamoroll.wallpaperlivewallpaperauth2.config.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cinnamoroll.wallpaperlivewallpaperauth2.R;
@Database(entities = {MyFavs.class},version = 1, exportSchema = false)
public abstract class MyDataBse extends RoomDatabase {
    public abstract FavDao favDao();
    private static MyDataBse Instance;
    public static MyDataBse getInstance(Context context){
        if (Instance==null){
           Instance = Room.databaseBuilder(context.getApplicationContext(),MyDataBse.class,"WallDb"+context.getString(R.string.app_name).replace(" ","")).allowMainThreadQueries().build();
        }
        return Instance;
    }
}
