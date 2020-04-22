package com.example.latihanapi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.latihanapi.Database.AppDatabase;
import com.example.latihanapi.Database.DataList;
import com.example.latihanapi.Database.EditItem;
import com.example.latihanapi.MainActivity;
import com.example.latihanapi.R;

public class EditList extends AppCompatActivity {

    ImageView poster;
    TextView title,release;
    EditText date,keterangan;
    Button submit;
    Integer id;
    String stitle,srelease,sposter,sdate,sdetail;
    DataList dataList = new DataList();
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        appDatabase = appDatabase.initDB(getApplicationContext());
        poster = findViewById(R.id.poster_edit);
        title = findViewById(R.id.judul_edit);
        release = findViewById(R.id.release_edit);
        date = findViewById(R.id.date_edit);
        keterangan = findViewById(R.id.keterangan_edit);
        submit = findViewById(R.id.submit_edit);
        Bundle bundle = getIntent().getExtras();
        sposter = bundle.getString("poster");
        stitle = bundle.getString("title");
        srelease = bundle.getString("release");
        sdate = bundle.getString("date");
        sdetail = bundle.getString("detail");
        id = bundle.getInt("id");
        title.setText(stitle);
        release.setText(srelease);
        date.setText(sdate);
        keterangan.setText(sdetail);
        Glide.with(this).load(BASE_IMAGE_URL+sposter).into(poster);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dataList.setTitle(stitle);
               dataList.setRelease(srelease);
               dataList.setPoster(sposter);
               dataList.setDate_watch(date.getText().toString());
               dataList.setDetail(keterangan.getText().toString());
               dataList.setId(id);
               new EditItem(appDatabase,dataList).execute();
                Intent intent = new Intent(EditList.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
