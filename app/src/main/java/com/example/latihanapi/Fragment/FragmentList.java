package com.example.latihanapi.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latihanapi.Adapter.ListAdapter;
import com.example.latihanapi.Database.AppDatabase;
import com.example.latihanapi.Database.DataList;
import com.example.latihanapi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentList extends Fragment {


    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private DataList listmovie[];
    private ArrayList<DataList> dataLists = new ArrayList<>();

    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDatabase = appDatabase.initDB(getContext().getApplicationContext());
        listmovie = appDatabase.dao().getData();
        recyclerView = view.findViewById(R.id.rvdatabase);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        for (int i=0; i < listmovie.length; i++)
        {
            dataLists.add(listmovie[i]);
        }
        ListAdapter Adapter = new ListAdapter(getContext());
        Adapter.setDataList(dataLists);
        recyclerView.setAdapter(Adapter);
    }

}
