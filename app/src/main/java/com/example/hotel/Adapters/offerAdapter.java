package com.example.hotel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.OrderActivity;
import com.example.hotel.PaymentActivity;
import com.example.hotel.R;
import com.example.hotel.Models.offerModel;
import com.example.hotel.Adapters.*;
import com.example.hotel.Models.*;
import com.example.hotel.Fragments.*;

import java.util.List;

public class offerAdapter extends RecyclerView.Adapter<offerAdapter.viewholder> {


    List<offerModel> modelList;
    Context context;

    OnActionClicked onActionClickListener;
    public interface OnActionClicked{

        void onActionClicked(View view, int position,offerModel model,TextView textView);

    }
    public  void setOnItemClickListener(OnActionClicked actionClickListener) {

        this.onActionClickListener = actionClickListener;
    }

    public offerAdapter(List<offerModel> modelList, Context context) {

        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.offer_layout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {

        final offerModel pos = modelList.get(position);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.offer_blink_animation);

        holder.desc.setText(pos.getDescription());
        holder.title.setText(pos.getTitle());
        holder.off.startAnimation(animation);
        //holder.imageView.
        Glide.with(context).load(pos.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onActionClickListener !=null){
                    onActionClickListener.onActionClicked(view,position,pos,holder.off);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView title,desc,off;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.offerTitle);
            off=itemView.findViewById(R.id.offTv);
            desc=itemView.findViewById(R.id.offerDesc);
            imageView=itemView.findViewById(R.id.offerImage);
        }
    }
}
