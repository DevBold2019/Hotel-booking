package com.example.hotel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotel.Adapters.FragmentAdapter;
import com.example.hotel.Fragments.AlcoholicDrinksFragment;
import com.example.hotel.Fragments.BeveragesFragment;
import com.example.hotel.Fragments.DessertsFragment;
import com.example.hotel.Fragments.MainsFragment;
import com.example.hotel.Fragments.RecommendedFragment;
import com.example.hotel.Fragments.SoftDrinksFragment;
import com.example.hotel.Models.dummyOrders;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    ViewPager viewPager;
    FragmentAdapter adapter;
    TabLayout tabLayout;
    Toolbar toolbar;
    ImageView imageView;
    Button button;

    List<dummyOrders>ordersList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        button = findViewById(R.id.goToCartBtn);

        toolbar = findViewById(R.id.detailsToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tabLayout);
        adapter=new FragmentAdapter(getSupportFragmentManager());

        adapter.addFragment(new RecommendedFragment(),"Recommended");
        adapter.addFragment(new MainsFragment(),"Mains");
        adapter.addFragment(new DessertsFragment(),"Desserts");
        adapter.addFragment(new BeveragesFragment(),"Beverages");
        adapter.addFragment(new SoftDrinksFragment(),"Soft Drinks");
        adapter.addFragment(new AlcoholicDrinksFragment(),"Alcoholic Drinks");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        loadData();

    }


    public  void showButton(String text){

        button.setVisibility(View.VISIBLE);
        button.setText(text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DetailsActivity.this,OrderActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);

            }
        });

    }



    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("dummy_cart", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("cart", null);

        Type type = new TypeToken<ArrayList<dummyOrders>>() {}.getType();
        ordersList = gson.fromJson(json, type);

        if (ordersList == null) {

            ordersList = new ArrayList<>();
            return;
        }

        for ( int i=0; i<ordersList.size(); i++){

            //Toast.makeText(this,"NAME:\t"+ordersList.get(i).getTitle(),Toast.LENGTH_SHORT).show();
            showButton("Order\t\t"+ordersList.size()+"\t\titems");
        }

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left, R.anim.slide_right_out);
    }

}