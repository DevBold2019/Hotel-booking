package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hotel.Adapters.*;
import com.example.hotel.Models.*;
import com.example.hotel.Fragments.*;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView,barRecyclerView;
    offerAdapter adapter;
    List<offerModel>modelList;
    List<restaurantsModel>modelList1;
    restaurantsAdapter adapter1;
    Animation animation;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);


        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_from_top);

        recyclerView=findViewById(R.id.offerRecyclerView);
        barRecyclerView=findViewById(R.id.restaurantsBarRecyclerView);

        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        barRecyclerView.setHasFixedSize(true);
        barRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //getWindow().getWindowManager().
        populateOffers();
        populateRestaurants();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.mapMenu:

                Intent intent=new Intent(HomeActivity.this,RestaurantBarMapActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);

                break;

        }

        return true;
    }

    public  void populateOffers(){

        //2 hotels 1 bar 2 cafe

        int pizzah = 0x1F355;
        int beer = 0x1F37B;
        int coffee = 0x2615;
        int ice = 0x1F367;
        int sun = 0x2600;

        String pizza= new String(Character.toChars(pizzah));
        String booze= new String(Character.toChars(beer));
        String kahawa= new String(Character.toChars(coffee));
        String ice_cream= new String(Character.toChars(ice));
        String jua= new String(Character.toChars(sun));

        modelList=new ArrayList<>();
        modelList.add(new offerModel("Bezo's restaurant",pizza+"10% off large pizza",R.drawable.hotel_4));
        modelList.add(new offerModel("Midnight bar & Lounge",booze+"offer on beer drinks",R.drawable.bar_1));
        modelList.add(new offerModel("Batian  Cafe' ",kahawa+"discounted coffee",R.drawable.cafe_1));
        modelList.add(new offerModel("Ice cool parlor ",ice_cream+"beat the"+jua+"with our 50%",R.drawable.cafe_4));



        adapter=new offerAdapter(modelList,HomeActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new offerAdapter.OnActionClicked() {
            @Override
            public void onActionClicked(View view, int position, offerModel model, TextView textView) {

                Intent intent=new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("offer_deal", modelList.get(position));
                intent.putExtra("ID",1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);
            }
        });

    }

    public  void populateRestaurants(){


        modelList1=new ArrayList<>();

        modelList1.add(new restaurantsModel("Full belly Resort"," home of Yummy steaks",R.drawable.hotel_2));
        modelList1.add(new restaurantsModel("Snacky Cafe'","Home of coffee",R.drawable.cafe_2));
        modelList1.add(new restaurantsModel("Century Bar' ","Join us for Karaoke night",R.drawable.bar_2));
        modelList1.add(new restaurantsModel("Legacy Hotel' ","Our food is to crave for!",R.drawable.hotel_3));
        modelList1.add(new restaurantsModel("Santuri's Lounge ","Perfect spot to hang out",R.drawable.bar));


        adapter1=new restaurantsAdapter(modelList1,HomeActivity.this);
        barRecyclerView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        adapter1.setOnItemClickListener(new restaurantsAdapter.OnActionClicked() {
            @Override
            public void onActionClicked(View view, int position, restaurantsModel model) {

                Intent intent=new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("offer_deal", modelList1.get(position));
                intent.putExtra("ID",2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);


            }
        });

    }

    }
