package com.example.lostfound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.MyViewHolder> {

    private List<String[]> dataList;
    private Context context;

    // Constructor to initialize the adapter with context and data list
    public Item_Adapter(Context context, List<String[]> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // Create view holder for each item in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    // Bind data to each item in the RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String[] data = dataList.get(position);
        holder.textView.setText(data[6].toUpperCase() + ": " + data[1]);
    }

    // Get the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // View holder class to hold the views for each item in the RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_name);

            // Set click listener for each item in the RecyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to navigate to the View_Item activity
                    Intent i = new Intent(context, View_Item.class);
                    i.putExtra("data", dataList.get(getAdapterPosition()));
                    context.startActivity(i); // Start the View_Item activity
                }
            });
        }
    }
}