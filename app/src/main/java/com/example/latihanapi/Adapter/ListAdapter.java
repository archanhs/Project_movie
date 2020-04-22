package com.example.latihanapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.latihanapi.Activity.EditList;
import com.example.latihanapi.Database.AppDatabase;
import com.example.latihanapi.Database.DataList;
import com.example.latihanapi.Database.DeleteList;
import com.example.latihanapi.Fragment.FragmentList;
import com.example.latihanapi.MainActivity;
import com.example.latihanapi.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private AppDatabase appDatabase;
    private DataList item = new DataList();

    public Context getContext() {
        return context;
    }

    private Context context;

    public void setDataList(ArrayList<DataList> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<DataList> getDataList() {
        return dataList;
    }

    private ArrayList<DataList> dataList = new ArrayList<DataList>();

    public ListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdb,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+getDataList().get(position).getPoster()).into(holder.poster);
        holder.title.setText(getDataList().get(position).getTitle());
        holder.release.setText("Release "+getDataList().get(position).getRelease());
        holder.detail.setText(getDataList().get(position).getDetail());
        holder.date.setText("Watch on "+getDataList().get(position).getDate_watch());
        holder.bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditList.class);
                intent.putExtra("poster",getDataList().get(position).getPoster());
                intent.putExtra("title",getDataList().get(position).getTitle());
                intent.putExtra("release",getDataList().get(position).getRelease());
                intent.putExtra("date",getDataList().get(position).getDate_watch());
                intent.putExtra("detail",getDataList().get(position).getDetail());
                intent.putExtra("id",getDataList().get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.bhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                item.setId(getDataList().get(position).getId());
                item.setPoster(getDataList().get(position).getPoster());
                item.setDetail(getDataList().get(position).getDetail());
                item.setRelease(getDataList().get(position).getRelease());
                item.setDate_watch(getDataList().get(position).getDate_watch());
                item.setTitle(getDataList().get(position).getTitle());
                appDatabase = appDatabase.initDB(getContext().getApplicationContext());
                Intent intent = new Intent(getContext(), MainActivity.class);
                context.startActivity(intent);
                new DeleteList(appDatabase,item).execute();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getDataList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView title,release,date,detail;
        private Button bedit,bhapus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.dbposter);
            title = itemView.findViewById(R.id.dbtitle);
            release = itemView.findViewById(R.id.dbrelease);
            date = itemView.findViewById(R.id.dbdate);
            detail = itemView.findViewById(R.id.dbdetail);
            bedit = itemView.findViewById(R.id.edit);
            bhapus = itemView.findViewById(R.id.hapus);
        }
    }
}
