package com.example.hotel.Fragments;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hotel.Adapters.itemsAdapter;
import com.example.hotel.DetailsActivity;
import com.example.hotel.Models.dummyOrders;
import com.example.hotel.Models.itemsModel;
import com.example.hotel.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class MainsFragment extends Fragment {

    RecyclerView recyclerView;
    itemsAdapter adapter;
    List<itemsModel> modelList;
    DetailsActivity details=new DetailsActivity();
    ArrayList<dummyOrders>ordersList;
    int quantity;
    Animation animation;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_mains, container, false);


        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.bounce_from_top);


        modelList=new ArrayList<>();
        ordersList=new ArrayList<>();

        recyclerView=view.findViewById(R.id.mainsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();


        return view;
    }

    public void loadData(){

        modelList.add(new itemsModel(1,R.drawable.food_1,"Fries and Meat","550","mm"));
        modelList.add(new itemsModel(2,R.drawable.food_2,"Burger","200","mm"));
        modelList.add(new itemsModel(3,R.drawable.food_3,"Pizza","1250","jj"));
        modelList.add(new itemsModel(4,R.drawable.food_4,"Sushi","2500","9uyt"));


        adapter=new itemsAdapter(modelList,getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new itemsAdapter.OnActionClicked() {
            @Override
            public void onActionClicked(View view, int position, itemsModel model, int quantity) {

                showDialog(model, position);

            }
        });






    }

    public  void showDialog(itemsModel model, int position){

        final Dialog dialog=new Dialog(getContext());



        final int image = model.getImage();
        final String name = model.getName();
        final String price = model.getPrice();

        dialog.setContentView(R.layout.details_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) (
                        displayRectangle.width() * 0.9f),
                dialog.getWindow().getAttributes().height);

        dialog.show();

        quantity=1;

        ImageButton addQty=dialog.findViewById(R.id.dialogAddToCartBtn);
        TextView removeQty=dialog.findViewById(R.id.dialogRemoveFromCart);
        final TextView Qty=dialog.findViewById(R.id.dialogItemQuantityTv);

        ImageView detailsImage=dialog.findViewById(R.id.detailsImage);
        ImageButton closeImage=dialog.findViewById(R.id.closeButton);
        final Button addImg=dialog.findViewById(R.id.addToOrder);
        TextView nameTv=dialog.findViewById(R.id.detailsName);

        Qty.setText(quantity+"\tx");
        nameTv.setText(name);
        detailsImage.startAnimation(animation);
        Glide.with(getContext()).load(image).into(detailsImage);

        addQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantity++;
                Qty.setText(quantity+"\tx");

            }
        });

        removeQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if (quantity>1){

                  quantity--;
                  Qty.setText(quantity+"\tx");
                  return;
              }



            }
        });



        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });


        addImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {


                ordersList.add(new dummyOrders(name,price,"desc",quantity,image));
                saveData(name);

                dialog.dismiss();

            }
        });

    }



    private void saveData(String name) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("dummy_cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ordersList);
        editor.putString("cart", json);
        editor.apply();
        Toast.makeText(getContext(),"Added :\t"+name,Toast.LENGTH_SHORT).show();
    }






}