package com.example.hotel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.Models.orderModel;
import com.example.hotel.R;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.viewholder> {

    List<orderModel>modelList;
    Context context;

    public orderAdapter(List<orderModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.order_layout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        orderModel pos = modelList.get(position);

        holder.quantityTv.setText(pos.getQuantity()+"\tx");
        holder.nameTv.setText(pos.getName());
        holder.descTv.setText(pos.getDescription());
        holder.priceTv.setText("KSH\t"+pos.getPrice());

        Glide.with(context).load(pos.getImage()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewholder extends  RecyclerView.ViewHolder {

        TextView quantityTv,nameTv,descTv,priceTv;
        ImageView imageView;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.orderImage);

            quantityTv=itemView.findViewById(R.id.quantityTV);
            nameTv=itemView.findViewById(R.id.orderNameTv);
            descTv=itemView.findViewById(R.id.orderDescriptionTv);
            priceTv=itemView.findViewById(R.id.orderPriceTv);


        }
    }
}
