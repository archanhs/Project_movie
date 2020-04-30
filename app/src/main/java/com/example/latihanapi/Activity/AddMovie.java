package com.example.latihanapi.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Locale;

public class AddMovie extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private EditText btDatePicker;

    ImageView poster;
    TextView title,release;
    EditText keterangan;
    Button submit;
    String stitle,srelease,sposter;
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private AppDatabase appDatabase;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        appDatabase = appDatabase.initDB(getApplicationContext());
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.judul);
        release = findViewById(R.id.release);
        submit = findViewById(R.id.submit);
        keterangan = findViewById(R.id.keterangan);
        Bundle bundle = getIntent().getExtras();
        sposter = bundle.getString("poster");
        stitle = bundle.getString("title");
        srelease = bundle.getString("release");
        title.setText(stitle);
        release.setText(srelease);
        Glide.with(this).load(BASE_IMAGE_URL+bundle.getString("poster")).into(poster);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = findViewById(R.id.date);
        btDatePicker = findViewById(R.id.date);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sketerangan = keterangan.getText().toString();

                String inputotomatis = String.valueOf(tvDateResult.getText().toString());


                DataList dataList = new DataList();
                dataList.setTitle(stitle);
                dataList.setPoster(sposter);
                dataList.setRelease(srelease);
                dataList.setDate_watch(inputotomatis);
                dataList.setDetail(sketerangan);
                appDatabase.dao().insertData(dataList);
                Intent intent = new Intent(AddMovie.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Data berhasil di submit silahkan cek di menu list",Toast.LENGTH_LONG).show();
            }
        });
    }
}
