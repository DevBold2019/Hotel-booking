package com.example.hotel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Models.tableModel;
import com.example.hotel.R;

import java.util.List;

public class tableAdapter extends RecyclerView.Adapter<tableAdapter.viewholder> {

    List<tableModel>modelList;
    Context context;

    public tableAdapter(List<tableModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.table_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        tableModel pos = modelList.get(position);

        holder.tbl_number.setText(pos.getTable_number());
        holder.seats.setText(pos.getSeats()+"\tSeats");

        if (pos.getOccupied()){
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.table_drawable_occupied));
            return;
        }

        holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.table_drawable));





    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView seats,tbl_number;
        LinearLayout linearLayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            seats=itemView.findViewById(R.id.seatsNoTv);
            tbl_number=itemView.findViewById(R.id.tableNoTv);

            linearLayout=itemView.findViewById(R.id.tblStatusLayout);



        }
    }
}
