package com.example.latihanapi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
public interface DataListDao {
    @Insert
    Long insertData(DataList dataList);

    @Query("Select * from list_movie")
    DataList[] getData();

    @Delete
    void deleteData(DataList item);

    @Update
    int updateData(DataList item);
}
