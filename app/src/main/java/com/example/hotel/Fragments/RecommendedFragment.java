package com.example.hotel.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hotel.BookTableActivity;
import com.example.hotel.Models.offerModel;
import com.example.hotel.Models.restaurantsModel;
import com.example.hotel.R;

public class RecommendedFragment extends Fragment {

    Button button;
    int beer = 0x1F37B;
    TextView textView,rest_name;
    ImageView imageView;
    ImageView loc_imageView;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.recommended_layout, container, false);

        textView=view.findViewById(R.id.offerEmoji);
        rest_name=view.findViewById(R.id.restaurantName);
        imageView=view.findViewById(R.id.detailsImage);
        loc_imageView=view.findViewById(R.id.locationImg);

        String booze= new String(Character.toChars(beer));
        textView.setText("off on beer\t"+booze);

        Intent intent = getActivity().getIntent();
        id=intent.getIntExtra("ID",0);

        if (id==1){

            offerModel offer = intent.getParcelableExtra("offer_deal");
            assert offer != null;
            rest_name.setText(offer.getTitle());
            Glide.with(getContext()).load(offer.getImage()).into(imageView);
            Glide.with(getContext()).load(offer.getImage()).into(loc_imageView);


        }else if (id==2){

            restaurantsModel restaurant = intent.getParcelableExtra("offer_deal");
            assert restaurant != null;
            rest_name.setText(restaurant.getTitle());
            Glide.with(getContext()).load(restaurant.getImage()).into(imageView);
            Glide.with(getContext()).load(restaurant.getImage()).into(loc_imageView);


        }



        button=view.findViewById(R.id.bookTableBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), BookTableActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_right,R.anim.slide_left_out);

            }
        });




        return view;
    }
}