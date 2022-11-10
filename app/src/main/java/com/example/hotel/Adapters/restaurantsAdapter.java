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
import com.example.hotel.DetailsActivity;
import com.example.hotel.Models.offerModel;
import com.example.hotel.R;
import com.example.hotel.Models.restaurantsModel;

import java.util.List;

public class restaurantsAdapter extends RecyclerView.Adapter<restaurantsAdapter.viewholder> {

    List<restaurantsModel>modelList;
    Context context;

    OnActionClicked onActionClickListener;
    public interface OnActionClicked{

        void onActionClicked(View view, int position, restaurantsModel model);

    }
    public  void setOnItemClickListener(OnActionClicked actionClickListener) {

        this.onActionClickListener = actionClickListener;
    }

    public restaurantsAdapter(List<restaurantsModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.restaurant_layout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {

        final restaurantsModel pos = modelList.get(position);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.blink_animation);

        holder.off.startAnimation(animation);
        holder.desc.setText(pos.getDescription());
        holder.title.setText(pos.getTitle());
        Glide.with(context).load(pos.getImage()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onActionClickListener !=null){
                    onActionClickListener.onActionClicked(view,position,pos);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView title,desc,off;
        ImageView imageView;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            off=itemView.findViewById(R.id.restOff);
            title=itemView.findViewById(R.id.restTitle);
            desc=itemView.findViewById(R.id.restDesc);
            imageView=itemView.findViewById(R.id.restImage);
        }
    }
}
