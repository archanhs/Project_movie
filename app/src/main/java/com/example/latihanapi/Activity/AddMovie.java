package com.example.latihanapi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.latihanapi.Database.AppDatabase;
import com.example.latihanapi.Database.DataList;
import com.example.latihanapi.Fragment.FragmentMovie;
import com.example.latihanapi.MainActivity;
import com.example.latihanapi.R;

public class AddMovie extends AppCompatActivity {

    ImageView poster;
    TextView title,release;
    EditText date,keterangan;
    Button submit;
    String stitle,srelease,sposter;
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        appDatabase = appDatabase.initDB(getApplicationContext());
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.judul);
        release = findViewById(R.id.release);
        date = findViewById(R.id.date);
        submit = findViewById(R.id.submit);
        keterangan = findViewById(R.id.keterangan);
        Bundle bundle = getIntent().getExtras();
        sposter = bundle.getString("poster");
        stitle = bundle.getString("title");
        srelease = bundle.getString("release");
        title.setText(stitle);
        release.setText(srelease);
        Glide.with(this).load(BASE_IMAGE_URL+bundle.getString("poster")).into(poster);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdate = date.getText().toString();
                String sketerangan = keterangan.getText().toString();

                DataList dataList = new DataList();
                dataList.setTitle(stitle);
                dataList.setPoster(sposter);
                dataList.setRelease(srelease);
                dataList.setDate_watch(sdate);
                dataList.setDetail(sketerangan);
                appDatabase.dao().insertData(dataList);
                Intent intent = new Intent(AddMovie.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Data berhasil di submit silahkan cek di menu list",Toast.LENGTH_LONG).show();
            }
        });
    }
}
