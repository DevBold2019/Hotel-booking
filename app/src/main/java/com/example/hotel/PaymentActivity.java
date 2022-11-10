package com.example.hotel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.hotel.Adapters.paymentAdapter;
import com.example.hotel.Models.paymentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    paymentAdapter adapter;
    List<paymentModel> modelList;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar=findViewById(R.id.paymentToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.paymentMethodRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        loadCards();


    }

    public  void loadCards(){

        modelList=new ArrayList<>();
        modelList.add(new paymentModel(R.drawable.debit_card,"Debit Card"));
        modelList.add(new paymentModel(R.drawable.credit_card,"Credit Card"));
        modelList.add(new paymentModel(R.drawable.paypal,"PayPal"));
        modelList.add(new paymentModel(R.drawable.phone_pay,"Mpesa"));

        adapter=new paymentAdapter(modelList,PaymentActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left, R.anim.slide_right_out);
    }

}