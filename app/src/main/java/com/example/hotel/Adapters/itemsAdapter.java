package com.example.hotel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.Models.itemsModel;
import com.example.hotel.R;

import java.util.List;

public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.viewholder> {

    List<itemsModel> modelList;
    Context context;
     int quantity;


    OnActionClicked onActionClickListener;
    public interface OnActionClicked{

        void onActionClicked(View view, int position,itemsModel model,int quantity);

    }
    public  void setOnItemClickListener(OnActionClicked actionClickListener) {

        this.onActionClickListener = actionClickListener;
    }

    public itemsAdapter(List<itemsModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_list_layout,parent,false);




        return  new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {

        final itemsModel pos = modelList.get(position);


        holder.name.setText(pos.getName());
        holder.price.setText("KSH\t"+pos.getPrice());
        Glide.with(context).load(pos.getImage()).into(holder.imageView);


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (onActionClickListener !=null){
                    onActionClickListener.onActionClicked(view,position,pos,quantity);
                }

            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantity--;

                holder.linearLayout.setVisibility(View.VISIBLE);
                holder.quantity.setText(quantity+"\tx");
                
                if (quantity <= 0){

                    quantity=0;
                    holder.linearLayout.setVisibility(View.GONE);
                    return;

                }

                if (onActionClickListener !=null){
                    onActionClickListener.onActionClicked(view,position,pos,quantity);
                }

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onActionClickListener !=null){
                    onActionClickListener.onActionClicked(view,position,pos,quantity);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name,price,quantity,remove;
        ImageView imageView;
        ImageButton add;
        LinearLayout linearLayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.itemNameTv);
            price=itemView.findViewById(R.id.itemPriceTv);
            quantity=itemView.findViewById(R.id.itemQuantityTv);
            remove=itemView.findViewById(R.id.removeFromCart);

            imageView=itemView.findViewById(R.id.itemImage);
            add=itemView.findViewById(R.id.addToCartBtn);

            linearLayout=itemView.findViewById(R.id.quantityLayout);


        }
    }
}
