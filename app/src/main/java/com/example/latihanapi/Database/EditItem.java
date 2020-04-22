package com.example.latihanapi.Database;

import android.os.AsyncTask;

public class EditItem extends AsyncTask<Void, Void, Integer> {
    private AppDatabase database;
    private DataList dataList;

    public EditItem(AppDatabase database, DataList dataList) {
        this.database = database;
        this.dataList = dataList;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return database.dao().updateData(dataList);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
