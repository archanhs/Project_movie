package com.example.latihanapi.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataList.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataListDao dao();
    private static AppDatabase appDatabase;

    public static AppDatabase initDB(Context context){
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context,AppDatabase.class,"dbDataList").allowMainThreadQueries().build();
        return appDatabase;
    }

}
