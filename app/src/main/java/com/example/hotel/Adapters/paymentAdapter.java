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
import com.example.hotel.R;
import com.example.hotel.Models.paymentModel;

import java.util.List;

public class paymentAdapter extends RecyclerView.Adapter<paymentAdapter.viewholder> {

    List<paymentModel> modelList;
    Context context;

    public paymentAdapter(List<paymentModel> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        paymentModel pos = modelList.get(position);

        holder.textView.setText(pos.getTitle());
        Glide.with(context).load(pos.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public class viewholder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.paymentImage);
            textView=itemView.findViewById(R.id.paymentTitle);

        }
    }
}
