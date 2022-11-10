package com.example.hotel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.hotel.Adapters.tableAdapter;
import com.example.hotel.Models.tableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookTableActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    tableAdapter adapter;
    List<tableModel> modelList;

    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        toolbar=findViewById(R.id.bookTableToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.tablesRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        modelList=new ArrayList<>();
        populateData();


    }

    public  void populateData(){

        modelList.add(new tableModel(false,"01","5"));
        modelList.add(new tableModel(true,"02","5"));
        modelList.add(new tableModel(false,"03","3"));
        modelList.add(new tableModel(true,"04","6"));
        modelList.add(new tableModel(true,"05","1"));
        modelList.add(new tableModel(false,"06","4"));
        modelList.add(new tableModel(true,"07","10"));
        modelList.add(new tableModel(false,"08","4"));

        adapter=new tableAdapter(modelList,BookTableActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left, R.anim.slide_right_out);
    }



}