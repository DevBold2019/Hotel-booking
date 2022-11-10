package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hotel.Adapters.orderAdapter;
import com.example.hotel.Models.dummyOrders;
import com.example.hotel.Models.orderModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    orderAdapter adapter;
    List<orderModel> list;
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback;
    Button proceedButton;
    Toolbar toolbar;

    List<dummyOrders>ordersList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar=findViewById(R.id.orderDetailsToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        proceedButton=findViewById(R.id.proceedButton);
        recyclerView=findViewById(R.id.ordersRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();


         simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                Toast.makeText(OrderActivity.this, "on Move", Toast.LENGTH_SHORT).show();

                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                final int position = viewHolder.getAdapterPosition();
                orderModel deleted=null;

                switch (swipeDir){

                    case ItemTouchHelper.RIGHT:

                        deleted=list.get(position);
                        list.remove(position);
                        adapter.notifyItemRemoved(position);


                        final orderModel finalDeleted = deleted;
                        Snackbar.make(recyclerView,"Remove:\t"+deleted.getName(),Snackbar.LENGTH_LONG).setAction("Undo",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        list.add(position, finalDeleted);
                                        adapter.notifyItemInserted(position);

                                    }
                                }).show();

                        break;

                    case ItemTouchHelper.LEFT:


                        break;




                }




            }

             @Override
             public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {


                 new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                         .addSwipeRightBackgroundColor(ContextCompat.getColor(OrderActivity.this, R.color.button_color))
                         .addSwipeRightActionIcon(R.drawable.delete_drawable)
                         .create()
                         .decorate();




                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
             }
         };

         loadData();

         proceedButton.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

                 Intent intent=new Intent(OrderActivity.this, PaymentActivity.class);
                 startActivity(intent);
                 overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);

             }
         });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left, R.anim.slide_right_out);
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


            String name,price,description;
            int quantity,image;

            quantity=ordersList.get(i).getQuantity();
            image=ordersList.get(i).getImage();
            name=ordersList.get(i).getTitle();
            price=ordersList.get(i).getPrice();
            description=ordersList.get(i).getDescription();


            list.add(new orderModel(String.valueOf(quantity),name,price,description,image));


            adapter=new orderAdapter(list,OrderActivity.this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);

        }

    }

    public void toast(String message){

        Toast.makeText(OrderActivity.this,message, Toast.LENGTH_SHORT).show();

    }
}