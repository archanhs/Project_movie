package com.example.latihanapi.Database;

import android.os.AsyncTask;

public class DeleteList extends AsyncTask<Void, Void, Void> {
    public DeleteList(AppDatabase database, DataList dataList) {
        this.database = database;
        this.dataList = dataList;
    }

    private AppDatabase database;
    private DataList dataList;
    @Override
    protected Void doInBackground(Void... voids) {
        database.dao().deleteData(dataList);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
