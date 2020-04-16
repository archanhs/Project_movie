package com.example.latihanapi.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface DataListDao {
    @Insert
    Long insertData(DataList dataList);

    @Query("Select * from list_movie")
    DataList[] getData();
}
